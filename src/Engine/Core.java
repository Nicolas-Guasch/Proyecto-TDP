package Engine;

import Broadcaster.*;
import Exceptions.TimeLineException;
import GameData.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
public final class Core
{
    // ------- Const --------------
    private final short FPS = GameSettings.GetInstance().FPS;

    // --------Singleton Stuff---------
    private static Core instance;
    static Core getInstance()
    {
        if (instance == null)
            instance = new Core();
        return instance;
    }
    private Core()
    {
        clean();
    }
    // -------- Broadcasters----------

    private Broadcaster<Float> onPhysicsUpdate;
    private Broadcaster onUpdate;
    private Invoker<Float> invokerOnPhysicsUpdate;
    private Invoker invokerOnUpdate;



    //----------- Core Variables ------------

    /*
     Long max value is 9.223.372.036.854.775.807
     you won't play for this cant of frames <3
    */
    private long currentFrame;
    //sorted map cause get the min is true order 1
    private SortedMap<Long,Queue<Runnable>> TasksForFrame;
    private boolean exit = false;//yeah, I know




    private void clean() // Initializer
    {
        Clock.Start();
        BroadcasterPackage<Float> packP = BroadcasterFactory.GetBroadcaster();
        invokerOnPhysicsUpdate = packP.Invoker;
        onPhysicsUpdate = packP.Broadcaster;

        BroadcasterPackage packU = BroadcasterFactory.GetBroadcaster();
        invokerOnUpdate = packU.Invoker;
        onUpdate = packU.Broadcaster;

        TasksForFrame = new TreeMap<>();// diamond expression, cuz we love the new java ♥
        currentFrame = 0;
        exit = false;

    }
    private void endOfFrame()
    {
        currentFrame ++;
        //Tasks ---------
        if(TasksForFrame.containsKey(currentFrame-1)){
            TasksForFrame.get(currentFrame-1).forEach(Runnable::run);
            TasksForFrame.remove(currentFrame-1);
        }
        invokerOnUpdate.Invoke(null);
    }



    private void mainLoop(){
        long stampPerFrame;
        long nanosperframe = (long)(1e9/FPS); //estimated
        float prev = 0f;
        float act;
        while(!exit)
        {
            stampPerFrame = Clock.currentTimeNanos();
            endOfFrame();
            do
            {
                act = Clock.currentTimeNanos();
                invokerOnPhysicsUpdate.Invoke((act-prev)/1_000_000_000f);
                prev = act;
            }
            while(Clock.currentTimeNanos() - stampPerFrame < nanosperframe);
        }
    }

    // --------- public stuff -----------
    void Start()
    {
        mainLoop();
    }
    // frames must be 0 based (put 0 for do at end of current frame)
    void waitForFrames(Runnable function, int frames)
    {
        if(frames<0)
            throw new TimeLineException("You cant make something before now");
        long targetFrame = frames+currentFrame;
        if(!TasksForFrame.containsKey(targetFrame))
        {
            LinkedBlockingQueue<Runnable> q = new LinkedBlockingQueue<>();
            q.add(function);
            TasksForFrame.put(targetFrame, q);

        }
        else{TasksForFrame.get(targetFrame).add(function);}
    }
    Broadcaster<Float> getPhysicsUpdater()
    {
        return onPhysicsUpdate;
    }
    Broadcaster getUpdater()
    {
        return onUpdate;
    }
}

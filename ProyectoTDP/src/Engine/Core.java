package Engine;

import Observer.*;
import Exceptions.TimeLineException;
import GameData.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * NO TOCAR!
 */
final class Core
{
    // ------- Const --------------
    private final short FPS = GameSettings.GetInstance().FPS;

    // --------Singleton Stuff---------
    private static Core instance;
    private AtomicBoolean paused = new AtomicBoolean(false);

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

    private IBroadcaster<Float> onPhysicsUpdate;
    private IBroadcaster<Object> onUpdate;
    private Invoker<Float> invokerOnPhysicsUpdate;
    private Invoker<Object> invokerOnUpdate;



    //----------- Core Variables ------------

    /*
     Long max value is 9.223.372.036.854.775.807
     you won't play for this cant of frames <3
    */
    private long currentFrame;
    //sorted map cause get the minim is true order 1
    private SortedMap<Long,Queue<Runnable>> TasksForFrame;
    private boolean exit = false;//yeah, I know




    private void clean() // Initializer
    {

        observerPack<Float> packP = ObserverSystem.getInstance().getBroadcaster();
        invokerOnPhysicsUpdate = packP.Invoker;
        onPhysicsUpdate = packP.Broadcaster;

        observerPack packU = ObserverSystem.getInstance().getBroadcaster();
        invokerOnUpdate = packU.Invoker;
        onUpdate = packU.Broadcaster;

        TasksForFrame = new TreeMap<>();// diamond expression, cuz we love the new java ♥
        currentFrame = 0;
        exit = false;

    }
    private void endOfFrame()
    {
        currentFrame ++; // esto es muy peligroso

        //Tasks ---------
        if(TasksForFrame.containsKey(currentFrame-1)){
            TasksForFrame.get(currentFrame-1).forEach(Runnable::run);
            TasksForFrame.remove(currentFrame-1);
        }
        invokerOnUpdate.invoke(null);
    }

    private long debt = 0;

    private void mainLoop(){
        long stampPerFrame;
        long nanosPerFrame = (long)(1e9/FPS);

        while(!exit)
        {
            if (paused.get()) {
                continue;
            }
            try {
                stampPerFrame = Clock.currentTimeNanos();
                endOfFrame();
                while (Clock.currentTimeNanos() - stampPerFrame < nanosPerFrame - debt) {
                    sleep(5);
                }
                debt = (Clock.currentTimeNanos() - stampPerFrame) - nanosPerFrame;
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }

    private void sleep(long time) {
        if(time<=0) return;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    // --------- interface stuff -----------

    void Start()
    {
        mainLoop();
    }
    // frames must be 0 based (put 0 for do at end of current frame)



    void waitForSeconds(Runnable action, float seconds)
    {
        waitForFrames(action,(int)seconds/FPS); // TODO: desmanijear
    }
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

    IBroadcaster<Float> getPhysicsUpdater()
    {
        return onPhysicsUpdate;
    }
    IBroadcaster<Object> getUpdater()
    {
        return onUpdate;
    }
    synchronized void setPaused(boolean p)
    {
        paused.lazySet(p);
    }
    synchronized boolean isPaused()
    {
        return  paused.get();
    }
    long frameCounter() {
        return currentFrame;
    }
}

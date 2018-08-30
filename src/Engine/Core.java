package Engine;

import Broadcaster.*;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

class Core // Singletone
{




    public Broadcaster<Float> OnFixedUpdate;
    private Invoker<Float> invokerOnFixedUpdate;


    private static final int FPS = 60;
    private static Core instance;
    static Core getInstance()
    {
        if (instance == null)
            instance = new Core();
        return instance;
    }

    /*
     Long max value is 9.223.372.036.854.775.807 ... you won't play for this cant of frames xD
    */
    private long currentFrame;


    private Queue<Component> toDoStartQueue;

    //sorted map cause get the min is true order 1
    private SortedMap<Long,Queue<Runnable>> TasksForFrame;

    private GameObject DummyRoot(){return GameObject.GetRoot();}

    private boolean exit = false;

    private Core()
    {
        clean();
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

    void AddAnStart(Component component)
    {
        toDoStartQueue.add(component);
    }

    private void endOfFrame()
    {
        // Start's --------
        Queue<Component> toDoStartQueueBackup = new LinkedBlockingQueue<>(toDoStartQueue);
        toDoStartQueue = new LinkedBlockingQueue<>();
        toDoStartQueueBackup.forEach(Component::Start);
        toDoStartQueueBackup.clear();
        currentFrame ++;
        //Tasks ---------
        if(TasksForFrame.containsKey(currentFrame-1)){
            TasksForFrame.get(currentFrame-1).forEach(Runnable::run);
            TasksForFrame.remove(currentFrame-1);
        }
        DummyRoot().preorderUpdate();
    }

    private void clean()
    {
        BroadcasterPackage<Float> pack = BroadcasterFactory.GetBroadcaster();
        invokerOnFixedUpdate = pack.Invoker;
        OnFixedUpdate = pack.Broadcaster;
        toDoStartQueue = new LinkedBlockingQueue<>(); // diamond expression, cuz we love the new java ♥
        TasksForFrame = new TreeMap<>();
        currentFrame = 0;
        exit = false;
    }

    private void physicsStuff()
    {
        DummyRoot().preorderFixedUpdate();
    }

    void Start()
    {
        mainLoop();
    }

    private void mainLoop(){
        long stampPerFrame;
        int millsPerFrame = 1000/FPS; //estimated
        float elapsed = 0;
        Clock.StampSomething(this);
        while(!exit)
        {
            elapsed = Clock.TimeElapsed(this);
            stampPerFrame = Clock.currentTimeMillis();
            endOfFrame();
            do
            {
                physicsStuff();
                invokerOnFixedUpdate.Invoke(elapsed);
            }
            while(Clock.currentTimeMillis() - stampPerFrame < millsPerFrame);
        }
    }

}

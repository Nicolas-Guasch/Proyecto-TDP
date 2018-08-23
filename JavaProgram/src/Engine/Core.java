package Engine;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Core // Singletone
{
    public static final int FPS = 60;

    private static Core instance;
    public static Core getInstance()
    {
        if (instance == null)
            instance = new Core();
        else
            instance = instance;
        return instance;
    }

    /*
     Long max value is 9.223.372.036.854.775.807 ... you won't play for this cant of frames xD
    */
    private long currentFrame;


    private Queue<Component> toDoStartQueue , toDoStartQueueBackup;

    //sorted map cause get the min is true order 1
    private SortedMap<Long,Queue<Runnable>> TasksForFrame;

    private GameObject DummyRoot(){return GameObject.GetRoot();};

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
        toDoStartQueueBackup = new LinkedBlockingQueue<>();
        toDoStartQueueBackup.addAll(toDoStartQueue);
        toDoStartQueue = new LinkedBlockingQueue<>();
        toDoStartQueueBackup.forEach((item)->{
            item.Start();
        });
        toDoStartQueueBackup.clear();
        toDoStartQueueBackup = null;
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

        toDoStartQueue = new LinkedBlockingQueue<>(); // diamond expression, cuz we love the new java ♥
        TasksForFrame = new TreeMap<>();
        currentFrame = 0;
        exit = false;
    }

    private void physicsStuff()
    {
        DummyRoot().preorderFixedUpdate();
    }

    public void Start(){
        mainLoop();
    }

    private void mainLoop(){
        long stampPerFrame;
        int millsPerFrame = 1000/FPS; //estimated
        //int fordebug = 2;
        while(!exit)
        {
            //fordebug = 0;
            stampPerFrame = Clock.currentTimeMillis();
            endOfFrame();

            do
            {
                physicsStuff();
                //fordebug ++;
                //System.out.println(fordebug +" -- "+ Clock.currentTimeMillis());
            }
            while(Clock.currentTimeMillis() - stampPerFrame < millsPerFrame);
        }
    }

}

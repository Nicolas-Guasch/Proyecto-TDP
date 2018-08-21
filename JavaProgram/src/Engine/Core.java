package Engine;

import DataStructures.Tree.*;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.*;

public class Core // Singletone
{
    private static Core instance;
    public static Core getInstance()
    {
        instance = instance == null ? new Core() : instance;
        return instance;
    }

    /*
     Long max value is 9.223.372.036.854.775.807 ... you won't play for this cant of frames xD
    */
    private long currentFrame;


    private Queue<Component> toDoStartQueue , toDoStartQueueBackup;

    //sorted map cause get the min is true order 1
    private SortedMap<Long,Queue<Runnable>> TasksForFrame;


    private GameObject DummyRoot;

    private Core()
    {
        DummyRoot = new GameObject();
        toDoStartQueue = new LinkedBlockingQueue<>(); // diamond expression, cuz we love the new java ♥
        TasksForFrame = new TreeMap<>();
        currentFrame = 0;
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
    }



}

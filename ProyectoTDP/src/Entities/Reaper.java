package Entities;

import Engine.Component;
import Engine.EngineFactory;
import Engine.GameObject;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Reaper extends Component
{
    private static Reaper instance;
    private static float lejos=1000;


    public static Reaper Instance()
    {
        if(instance == null){
            instance = GameObject.getRoot().addChild().addComponent(new Reaper());
        }
        return instance;
    }


    private Collection<Entity> entities;
    private Queue<Entity> toDestroy;

    private Reaper()
    {
        entities = new LinkedList<>();
        toDestroy = new LinkedBlockingQueue<>();
    }

    @Override
    public void Update()
    {

        while(!toDestroy.isEmpty())
        {
            toDestroy.remove().getReferenced().Destroy();
        }
        entities.forEach((e)->{
            if(e.getData().getHealth()<=0 || e.getReferenced().getTransform().position().length()>lejos){
                toDestroy.add(e);
                e.onDeath();
            }
        });
        toDestroy.forEach((e)->entities.remove(e));

    }

    public void add(Entity ent)
    {
        entities.add(ent);
    }
    public void remove(Entity ent)
    {
        entities.remove(ent);
    }
    public void killIn(Entity ent, int frames)
    {
        EngineFactory.Instance().get().WaitForFrames(()-> ent.getData().setHealth(-1),frames);
    }
    public void KillThemAll()
    {
        for(var ent : entities)
        {
            killIn(ent,1);
        }
    }
}

package Entities;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * is just the fucking Grim Reaper
 */
public class TheGrimReaper extends Component
{
    private static TheGrimReaper instance;
    private static float far = 1500;


    public static TheGrimReaper Instance()
    {
        if(instance == null){
            instance = GameObject.getRoot().addChild().addComponent(new TheGrimReaper());
        }
        return instance;
    }


    private Collection<Entity> entities;
    private Queue<Entity> toDestroy;

    private TheGrimReaper()
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
            if(e.getData().getHealth()<=0 || e.getReferenced().getTransform().position().length()> far){
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
        EngineGetter.Instance().get().WaitForFrames(()-> ent.getData().setHealth(-1),frames);
    }
    public void KillThemAll()
    {
        for(var ent : entities)
        {
            killIn(ent,1);
        }
    }
}

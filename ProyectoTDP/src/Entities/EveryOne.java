package Entities;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;
import EntitiesVisitor.EnemiesCounter;
import EntitiesVisitor.VisitorEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * is just the fucking Grim Reaper
 */
public class EveryOne extends Component
{
    private static EveryOne instance;
    private static float far = 1500;
    private Queue<Entity> toAdd;
    private Queue<VisitorEntity> visitors;


    public static EveryOne getInstance()
    {
        if(instance == null){
            instance = GameObject.getRoot().addChild().addComponent(new EveryOne());
        }
        return instance;
    }


    private Collection<Entity> entities;
    private Queue<Entity> toDestroy;

    private EveryOne()
    {
        entities = new LinkedList<>();
        toDestroy = new LinkedBlockingQueue<>();
        toAdd = new LinkedBlockingQueue<>();
        visitors = new LinkedBlockingQueue<>();
    }

    @Override
    public void Update()
    {

        while(!toDestroy.isEmpty())
        {
            toDestroy.remove().referenced().Destroy();
        }
        while(!toAdd.isEmpty())
        {
            entities.add(toAdd.remove());
        }
        entities.forEach(this::accept);
        toDestroy.forEach((e)->entities.remove(e));
        doForeach();
    }

    public void add(Entity ent)
    {
        toAdd.add(ent);
    }
    public void remove(Entity ent)
    {
        entities.remove(ent);
    }
    public void killIn(Entity ent, int frames)
    {
        EngineGetter.Instance().get().waitForFrames(()-> ent.data().setHealth(-1),frames);
    }
    public void KillThemAll()
    {
        for(var ent : entities)
        {
            killIn(ent,1);
        }
    }

    private void doForeach(){
        while(!visitors.isEmpty()){
            var visitor = visitors.remove();
            entities.forEach(e->e.accept(visitor));
        }
    }

    public void forEach(VisitorEntity visitor){
        visitors.add(visitor);
    }

    private void accept(Entity e) {
        if(e.data() == null) return;
        if (e.data().getHealth() <= 0 || e.referenced().transform().position().length() > far) {
            toDestroy.add(e);
            e.onDeath();
        }
    }

    public void fastForEach(VisitorEntity visitor) {
        entities.forEach(e->e.accept(visitor));
    }
}

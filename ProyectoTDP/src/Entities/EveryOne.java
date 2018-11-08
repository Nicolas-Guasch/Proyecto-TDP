package Entities;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;
import EntitiesVisitor.VisitorEntity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 Administra la destruccion de las entidades que murieron
 */
public class EveryOne extends Component
{
    private static float far = 2000;

    private static EveryOne instance;
    public static EveryOne getInstance()
    {
        if(instance == null){
            instance = GameObject.getRoot().addChild().addComponent(new EveryOne());
        }
        return instance;
    }

    private Queue<VisitorEntity> visitors;
    private Collection<Entity> entities;

    private Queue<Entity> toAdd;
    private Queue<Entity> toDestroy;

    private EveryOne()
    {
        entities = new LinkedList<>();

        toDestroy = new LinkedBlockingQueue<>();
        toAdd = new LinkedBlockingQueue<>();
        visitors = new LinkedBlockingQueue<>();
    }

    @Override
    public void update()
    {
        while(!toDestroy.isEmpty())
        {
            toDestroy.remove().referenced().destroy();
        }
        while(!toAdd.isEmpty())
        {
            entities.add(toAdd.remove());
        }
        entities.forEach(this::checkDestroyable);
        toDestroy.forEach((e)->entities.remove(e));

        acceptVisitors();
    }

    public void add(Entity ent)
    {
        toAdd.add(ent);
    }

    public void remove(Entity ent)
    {
        toDestroy.add(ent);

        //TODO: puede ser peligroso, usar una cola auxiliar
    }
    public void killIn(Entity ent, int frames)
    {
        EngineGetter.Instance().get().waitForFrames(()-> {
            if(ent.data()==null)return;
            ent.data().setHealth(-1);

        },frames);
    }


    /**
     * Kill al entities
     */
    public void killThemAll()
    {
        for(var ent : entities)
        {
            killIn(ent,1);
        }
    }
    /**
     * send a visitor for each entity at the end of the cycle
     * @param visitor visitor to send
     */
    public void takeLazyVisitor(VisitorEntity visitor){
        visitors.add(visitor);
    }

    /**
     * send a visitor for each entity
     * (warning, this could be executed in the middle of the loop)
     * @param visitor visitor to send
     */
    public void takeVisitor(VisitorEntity visitor) {
        entities.forEach(e->e.accept(visitor));
    }

    private void acceptVisitors(){
        while(!visitors.isEmpty()){
            var vis = visitors.remove();
            entities.forEach(e->e.accept(vis));
        }
    }

    private void checkDestroyable(Entity e) {
        if(e.data() == null) return;
        if (e.data().getHealth() <= 0 || e.referenced().transform().position().length() > far) {
            toDestroy.add(e);
            e.onDeath();
        }
    }

    public Iterable<Entity> getEntities()
    {
        //return entities_BU;
        return new LinkedList<>(entities); // no es tan eficiente pero me sirve
    }


}

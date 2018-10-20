package IAs;

import ADTs.Vector2;
import Entities.Entity;

import java.util.function.Predicate;

public class SwitchWhen implements EntityQuery {



    private Predicate<Entity> predicate;
    private EntityQuery currentHandler;
    private final EntityQuery otherHandler;

    public SwitchWhen(Predicate<Entity> predicate, EntityQuery handlerFirst, EntityQuery handlerLast) {
        this.predicate = predicate;
        this.currentHandler = handlerFirst;
        this.otherHandler = handlerLast;
    }


    private void check(Entity e){
        if(predicate.test(e)){
            currentHandler = otherHandler;
        }
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        check(ent);
        return currentHandler.whereToMove(ent);
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        check(ent);
        return currentHandler.whereToSee(ent);
    }
}

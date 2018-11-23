package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;

public class Hybrid50Hunter extends AIQueryDecorator {

    public Hybrid50Hunter(EntityQuery handler) {
        super(handler);
    }

    @Override
    public IVector2 whereToMove(Entity ent) {
        if(ent.data().getHealth() < 50)
        {
            return decorated.whereToMove(ent).withLength(0.5f).sum(ent.referenced().transform().top(1.5f)).norma();
        }
        return decorated.whereToMove(ent);
    }

    @Override
    public IVector2 whereToSee(Entity ent) {
        return decorated.whereToSee(ent);
    }
}

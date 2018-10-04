package IAs;

import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Entity;

public class Hybrid50Hunter extends AIQueryDecorator {

    private Transform toFollow;

    public Hybrid50Hunter(EntityQuery handler, Transform transform) {
        super(handler);
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        if(ent.getData().getHealth() < 50)
        {
            return decorated.whereToMove(ent).withLength(0.5f).sum(ent.getReferenced().getTransform().top(1.5f)).versor();
        }
        return decorated.whereToMove(ent);
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return decorated.whereToSee(ent);
    }
}

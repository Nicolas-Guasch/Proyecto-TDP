package IAs;

import ADTs.Vector2;
import Entities.Entity;

public abstract class ParametricMove extends AIQueryDecorator{

    private int t;
    private Vector2 lastDirection = Vector2.DOWN();

    public ParametricMove(EntityQuery decorated) {
        super(decorated);
        t=0;
    }

    @Override
    public final Vector2 whereToMove(Entity ent)
    {
        var lastDirection = whereToMove(ent,t++);
        return lastDirection;
    }
    public abstract Vector2 whereToMove(Entity ent, int t);

    @Override
    public final Vector2 whereToSee(Entity ent) {
        return lastDirection.norma();
    }

}

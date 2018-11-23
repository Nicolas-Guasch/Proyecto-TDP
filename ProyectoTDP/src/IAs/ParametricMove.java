package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;

public abstract class ParametricMove extends AIQueryDecorator{

    private int t;
    private IVector2 lastDirection = Vector2.DOWN();

    public ParametricMove(EntityQuery decorated) {
        super(decorated);
        t=0;
    }

    @Override
    public final IVector2 whereToMove(Entity ent)
    {
        IVector2 lastDirection = whereToMove(ent,t++);
        return lastDirection;
    }
    public abstract IVector2 whereToMove(Entity ent, int t);

    @Override
    public final IVector2 whereToSee(Entity ent) {
        return lastDirection.norma();
    }

}

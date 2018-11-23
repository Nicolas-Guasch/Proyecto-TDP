package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;

public class Spinner extends AIQueryDecorator {


    private IVector2 lastDirection = Vector2.DOWN();

    public Spinner(EntityQuery decorated) {
        super(decorated);
    }

    @Override
    public IVector2 whereToMove(Entity ent) {
        return decorated.whereToMove(ent);
    }

    @Override
    public IVector2 whereToSee(Entity ent) {
        lastDirection = lastDirection.rotateUnary(0.01f);
        return lastDirection;
    }
}

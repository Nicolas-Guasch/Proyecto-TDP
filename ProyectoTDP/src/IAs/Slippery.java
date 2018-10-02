package IAs;

import Engine.Vector2;
import Entities.Entity;

public class Slippery extends AIQueryDecorator
{


    public Slippery(EntityQuery decorated) {
        super(decorated);
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        return null;
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return null;
    }
}

package IAs;

import Engine.Components.Transform;
import ADTs.Vector2;
import Entities.Entity;

public class WatchAnother extends AIQueryDecorator
{

    private final Transform another;
    private int scapeFrom = 1;
    public WatchAnother(Transform another, EntityQuery decorated)
    {
        super(decorated);
        this.another = another;

    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        return Vector2.ORIGIN();
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return another.position().sub(ent.referenced().transform().position()).prod(scapeFrom);
    }
}

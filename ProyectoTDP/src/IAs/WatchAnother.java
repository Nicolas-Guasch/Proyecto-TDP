package IAs;

import Engine.Components.Transform;
import Engine.Vector2;
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

    public WatchAnother(Transform another, boolean scapeFrom, EntityQuery decorated)
    {
        this(another,decorated);
        this.scapeFrom = scapeFrom?-1:1;
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        return Vector2.ORIGIN();
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return another.position().minus(ent.getReferenced().getTransform().position()).prod(scapeFrom);
    }
}

package IAs;

import ADTs.Vector2;
import Entities.Entity;

public class DummyEntityQuery implements EntityQuery
{
    @Override
    public Vector2 whereToMove(Entity ent) {
        return Vector2.ORIGIN();
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return ent.referenced().transform().top();
    }
}

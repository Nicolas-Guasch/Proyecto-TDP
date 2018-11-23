package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;

public class DummyEntityQuery implements EntityQuery
{
    @Override
    public IVector2 whereToMove(Entity ent) {
        return Vector2.ORIGIN();
    }

    @Override
    public IVector2 whereToSee(Entity ent) {
        return ent.referenced().transform().top();
    }
}

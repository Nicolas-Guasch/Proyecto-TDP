package IAs;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;

public interface EntityQuery
{
    IVector2 whereToMove(Entity ent);
    IVector2 whereToSee(Entity ent);
}

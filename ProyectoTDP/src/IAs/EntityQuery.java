package IAs;

import ADTs.Vector2;
import Entities.Entity;

public interface EntityQuery
{
    Vector2 whereToMove(Entity ent);
    Vector2 whereToSee(Entity ent);
}

package IAs;

import ADTs.Vector2;
import Entities.Entity;

public class VaderAngerManagement extends AIQueryDecorator
{


    public VaderAngerManagement(EntityQuery decorated) {
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

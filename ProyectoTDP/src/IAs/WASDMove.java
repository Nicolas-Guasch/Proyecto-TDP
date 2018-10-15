package IAs;

import ADTs.Vector2;
import Entities.Entity;

public class WASDMove extends AIQueryDecorator {
    public WASDMove(EntityQuery h) {
        super(h);
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

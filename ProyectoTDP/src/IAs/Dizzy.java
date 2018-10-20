package IAs;

import ADTs.Vector2;
import Entities.Entity;

public class Dizzy extends AIQueryDecorator
{
    public Dizzy(EntityQuery decorated) {
        super(decorated);
        this.decorated = new EllipseMove(decorated,1,1);
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        return decorated.whereToMove(ent);
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return decorated.whereToMove(ent).rotateUnary(0.5f);
    }
}

package IAs;

import Engine.Vector2;
import Entities.Entity;

public class Dizzy extends AIQueryDecorator
{
    public Dizzy(EntityQuery decorated) {
        super(decorated);
        this.decorated = new EllipseMove(decorated);
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        return decorated.whereToMove(ent);//TODO: buscar cosas
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return null;//TODO: hacer algo
    }
}

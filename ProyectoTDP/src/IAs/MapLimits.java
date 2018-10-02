package IAs;

import Engine.Vector2;
import Entities.Entity;

public class MapLimits extends AIQueryDecorator
{
    private final Vector2 topRight;
    private final Vector2 bottomLeft;

    public MapLimits(Vector2 bottomLeft, Vector2 topRight, EntityQuery decorated) {
        super(decorated);
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    private Vector2 pos(Entity ent){
        return ent.getReferenced().getTransform().position();
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        Vector2 v = decorated.whereToMove(ent);
        Vector2 pos = pos(ent);
        if(pos.over(topRight))
        {
            v = Vector2.DOWN();
        }
        if(pos.under(bottomLeft))
        {
            v = Vector2.UP();
        }
        if(pos.onRight(topRight))
        {
            v = Vector2.LEFT();
        }
        if(pos.onLeft(bottomLeft))
        {
            v = Vector2.RIGHT();
        }
        return v;
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return decorated.whereToSee(ent);
    }
}

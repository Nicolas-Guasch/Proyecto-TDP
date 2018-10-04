package IAs;

import Engine.Vector2;
import Entities.Entity;

public class Slippery extends AIQueryDecorator
{


    public Slippery(EntityQuery decorated) {
        super(decorated);
    }

    float tolerance = 0.0001f;
    Vector2 last = Vector2.LEFT(tolerance);

    @Override
    public Vector2 whereToMove(Entity ent) {
        Vector2 v = decorated.whereToMove(ent);
        if(last.equals(Vector2.ORIGIN())){
            last = v;
            return v;
        }
        if(v.versor().distanceTo(last.versor())<0.1f)
        {
            v = v.prod(0.5f);
            v = v.sum(last.prod(0.5f));
            last = v;
            return v;
        }
        if(v.length()<tolerance)
        {
            v = last.prod(0.9f);
        }
        last = v;
        return v;
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return decorated.whereToSee(ent);
    }
}

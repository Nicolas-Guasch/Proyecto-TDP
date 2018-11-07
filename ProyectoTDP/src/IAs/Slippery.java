package IAs;

import ADTs.Vector2;
import Entities.Entity;

public class Slippery extends AIQueryDecorator
{


    private float level;
    private float midleDispersion;

    public Slippery(EntityQuery decorated) {
        super(decorated);
        this.level = 0.9f;
        this.midleDispersion = 0.5f;
    }
    public Slippery(EntityQuery decorated, float level, float midleDispersion) {
        super(decorated);
        this.level = level;
        this.midleDispersion = midleDispersion;
    }

    private float tolerance = 0.0001f;
    private Vector2 last = Vector2.LEFT(tolerance);

    @Override
    public Vector2 whereToMove(Entity ent) {
        Vector2 v = decorated.whereToMove(ent);
        if(last.equals(Vector2.ORIGIN())){
            last = v;
            return v;
        }
        if(v.norma().distanceTo(last.norma())<0.1f)
        {
            v = v.prod(midleDispersion);
            v = v.sum(last.prod(midleDispersion));
            last = v;
            return v;
        }
        if(v.length()<tolerance)
        {
            v = last.prod(level);
        }
        last = v;
        return v;
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return decorated.whereToSee(ent);
    }
}

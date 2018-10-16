package IAs;

import ADTs.Vector2;
import Entities.Entity;

import java.util.Random;

public class AbsoluteLateral  extends AIQueryDecorator
{
    private int steps;
    private int i;
    private int speed;
    private boolean es_primer_pasada;
    public int ran;

    public AbsoluteLateral(EntityQuery decorated, int steps)
    {
        super(decorated);
        i=0;
        this.steps = steps;
        speed = 1;
        es_primer_pasada = true;
        ran = 0;
    }



    @Override
    public Vector2 whereToMove(Entity ent)
    {
        if(i>=steps + ran() || (es_primer_pasada && i>=steps/2))
        {
            es_primer_pasada = false;
            speed *= -1;
            i=0;
        }
        i++;
        var vec = Vector2.RIGHT(speed).sum(decorated.whereToMove(ent));
        return vec;

    }

    private int ran() {
        return new Random(hashCode()).nextInt(ran*ran+1);
    }

    @Override
    public Vector2 whereToSee(Entity ent)
    {
        return decorated.whereToSee(ent);
    }

    public void setRandomLevel(int x) {
        ran = x;
    }
}
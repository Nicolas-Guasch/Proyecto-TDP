package IAs;

import Engine.Vector2;
import Entities.Entity;

public class AbsoluteLateral  extends AIQueryDecorator
{
    private int steps;
    private int i;
    private int speed;

    public AbsoluteLateral(EntityQuery decorated, int steps)
    {
        super(decorated);
        i=0;
        this.steps = steps;
        speed = 1;
    }



    @Override
    public Vector2 whereToMove(Entity ent)
    {
        if(i>=steps)
        {
            speed *= -1;
            i=0;
            i++;
        }
        return Vector2.RIGHT(speed).sum(decorated.whereToMove(ent));
    }

    @Override
    public Vector2 whereToSee(Entity ent)
    {
        return decorated.whereToSee(ent);
    }
}
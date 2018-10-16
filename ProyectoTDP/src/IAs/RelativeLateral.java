package IAs;

import ADTs.Vector2;
import Entities.Entity;



/*
 Hace que se mueva hacia su izquierda y derecha relativa al top
 */
public class RelativeLateral extends AIQueryDecorator
{
    private int steps;
    private int i;
    private int speed;
    public RelativeLateral( EntityQuery decorated,int steps)
    {
        super(decorated);
        i=0;
        this.steps = steps;
        speed = 1;
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        if(i>=steps)
        {
            speed *= -1;
            i=0;
        }
        return ent.referenced().transform().top().right(speed);
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return ent.referenced().transform().top();
    }
}

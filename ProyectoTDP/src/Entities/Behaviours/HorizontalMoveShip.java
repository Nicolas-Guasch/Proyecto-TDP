package Entities.Behaviours;

import Engine.Component;
import ADTs.Vector2;
import Tools.Random;

/**
 *  Implements the horizontal movement of a ship
 *  with a max*speed*2 as the max distance to do
 */
public class HorizontalMoveShip extends Component
{

    private final int max;
    private Vector2 dir;
    private int counter;

    public HorizontalMoveShip(float speed, int max)
    {
        this.max = max+ Random.value(1,3);
        this.dir = Vector2.LEFT(speed * Random.value()*4);

    }

    public void update()
    {
        if(counter <=max)
        {
            transform().moveTowards(dir);
        }
        else
        {
            counter =0;
            dir = dir.prod(-1);
        }
        counter++;
    }
}

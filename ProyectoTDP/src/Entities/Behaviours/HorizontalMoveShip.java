package Entities.Behaviours;

import Engine.Component;
import Engine.Vector2;
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
        this.max = max+ Random.value(0,4);
        this.dir = Vector2.LEFT(speed * Random.value());
    }

    public void Update()
    {
        if(counter <=max)
        {
            transform().MoveTowards(dir);
        }
        else
        {
            counter =0;
            dir = dir.prod(-1);
        }
        counter++;
    }
}

package Entities.Behaviours;

import Engine.Component;
import Engine.Vector2;
import Tools.Random;

public class LinedEnemy extends Component
{
    //Se mueve hacia los lados

    private final int max;
    private Vector2 dir;
    private int counter;

    public LinedEnemy(float speed, int max)
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

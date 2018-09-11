package ParaTestear;

import Engine.Component;
import Engine.Vector2;

import java.util.Random;

public class Mareado extends Component
{

    private static final float max = 400;
    private static final float min = 0.001f;
    private float y;
    private float x;
    private float speed;
    private boolean downX = false;
    private boolean downY = false;



    public Mareado(float speed)
    {
        this.speed = speed;
        x = new Random(hashCode()).nextInt(200)+100;
        y = new Random(hashCode()).nextInt(200)+100;
    }

    public void Update()
    {
        if(downX)
        {
            x*=0.2f;
        }
        else
        {
            x*=1.2f;
        }
        if(downY)
        {
            y*=0.4f ;
        }
        else
        {
            y*=1.3f;
        }
        if(x > max)
        {
            downX = true;
        }
        if(x<min)
        {
            downX = false;
        }
        if(y > max)
        {
            downY = true;
        }
        if(y<min)
        {
            downY = false;
        }
        transform().MoveTowards(Vector2.Random(speed));

    }

}

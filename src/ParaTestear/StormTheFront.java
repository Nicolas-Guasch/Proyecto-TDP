package ParaTestear;

import Engine.Component;
import Engine.Vector2;

public class StormTheFront extends Component
{

    private int freq;
    private int i=0;
    private float speed;

    public StormTheFront(int freq, float speed)
    {
        this.freq=freq;
        this.speed = speed;
    }

    @Override
    public void Update()
    {
        if(i>=freq)
        {
            Vector2 point = transform().getPosition().sum(transform().getTop().prod(10));
            LaserMaker.create(gameObject(),speed).getTransform().setPosition(point);
            i=0;
        }
        i++;
    }
}

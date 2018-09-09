package ParaTestear;

import Engine.Component;
import Engine.GameObject;
import Engine.Vector2;

public class StormTheFront extends Component
{

    private int freq;
    private int i=0;
    private float speed;
    private float phaseShift;
    private boolean left;

    public StormTheFront(int freq, float speed, float phaseShift)
    {
        this.freq=freq;
        this.speed = speed;
        this.phaseShift = phaseShift;
        left = true;
        i=freq;
    }

    @Override
    public void Update()
    {
        if(i>=freq)
        {
            Vector2 point = transform().getPosition().sum(transform().getTop().prod(10));

            Vector2 desph = transform().getTop().swapped();

            if(left)
            {
                point = point.sum(desph.prod(phaseShift));
            }
            else
            {
                point = point.minus(desph.prod(phaseShift));
            }
            left = !left;

            LaserMaker.create(gameObject(),speed,0.5f).getTransform().setPosition(point);

            i=0;
        }
        i++;
    }
}

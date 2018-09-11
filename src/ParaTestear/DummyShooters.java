package ParaTestear;

import Engine.Component;
import Engine.GameObject;
import Engine.Vector2;

import java.util.Random;

public class DummyShooters extends Component
{

    private int freq;
    private int i=0;
    private float speed;
    private float phaseShift;
    private boolean left;

    public DummyShooters(int freq, float speed, float phaseShift)
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

            Vector2 lateral = new Vector2(new Random().nextInt(10)-5,-8).withLength(speed);

            LaserMaker.create(gameObject(),lateral).getTransform().setPosition(point);

            i=0;
        }
        i++;
    }
}

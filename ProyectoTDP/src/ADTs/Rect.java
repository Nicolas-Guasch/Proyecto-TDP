package ADTs;

import Engine.Vector2;
import UsefulInterfaces.IClonable;

public final class Rect implements IClonable<Rect>
{
    private Vector2 max, min;

    public Rect(Vector2 topRight, Vector2 bottomLeft)
    {
        this.max = topRight;
        this.min = bottomLeft;
    }

    public Vector2 center()
    {
        return max.sum(min).prod(0.5f);
    }

    public Vector2 min() {
        return min;
    }

    public Vector2 max() {
        return max;
    }

    public Rect displaced(Vector2 phaseShift)
    {
        return new Rect(max.sum(phaseShift), min.sum(phaseShift));
    }

    public Rect prod(float factor)
    {
        return new Rect(max.prod(factor), min.prod(factor));
    }


    @Override
    public Rect clone() {
        return new Rect(min,max);
    }
}

package ADTs;

import UsefulInterfaces.IClonable;

public final class Rect implements IClonable<Rect>
{
    private Vector2 max, min;

    public Rect(Vector2 topRight, Vector2 bottomLeft)
    {
        this.max = topRight;
        this.min = bottomLeft;
    }
    //something to push



    public Vector2 max() {
        return max;
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

package ADTs;


public final class Rect //fixme no tiene mas ESA herencia (iclonable)
{
    private Vector2 max, min;

    public Rect(Vector2 max, Vector2 min)
    {
        this.max = max;
        this.min = min;
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

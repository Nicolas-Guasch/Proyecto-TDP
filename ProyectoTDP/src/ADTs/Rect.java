package ADTs;


public final class Rect implements IRect
//fixme no tiene mas ESA herencia (iclonable) .Done
{
    private IVector2 max, min;

    public Rect(IVector2 max, IVector2 min)
    {
        this.max = max;
        this.min = min;
    }
    //something to push



    public IVector2 max() {
        return max;
    }

    public Rect prod(float factor)
    {
        return new Rect(max.prod(factor), min.prod(factor));
    }



    public Rect clone() {
        return new Rect(min,max);
    }
}

package GameTimeLine;

public abstract class TimePoint
{

    public abstract void assembleMoment();
    public abstract void startMoment();
    public abstract boolean completed();
    public void clean(){}
}

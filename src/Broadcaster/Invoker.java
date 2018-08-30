package Broadcaster;

public class Invoker<P>
{
    private Broadcaster<P> broadcaster;
    Invoker(Broadcaster<P> broad)
    {
        broadcaster = broad;
    }
    public void Invoke(P p)
    {
        broadcaster.Invoke(p);
    }
}

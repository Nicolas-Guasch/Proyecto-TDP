package Broadcaster;

public class Invoker<ParameterType>
{
    private Broadcaster<ParameterType> broadcaster;
    Invoker(Broadcaster<ParameterType> broad)
    {
        broadcaster = broad;
    }
    public void Invoke(ParameterType p)
    {
        broadcaster.Invoke(p);
    }
}

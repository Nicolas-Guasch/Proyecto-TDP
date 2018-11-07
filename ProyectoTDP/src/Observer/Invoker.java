package Observer;

public class Invoker<ParameterType>
{
    private Broadcaster<ParameterType> broadcaster;
    Invoker(Broadcaster<ParameterType> broad)
    {
        broadcaster = broad;
    }
    public void invoke(ParameterType p)
    {
        broadcaster.invoke(p);
    }
}

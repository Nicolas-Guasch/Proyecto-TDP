package Broadcaster;

public class ObserverPack<ParameterType>
{
    public IBroadcaster<ParameterType> Broadcaster;
    public Invoker<ParameterType> Invoker;
}

package Broadcaster;


public class GetBroadcaster
{
    /**
     * Gets a package with an Broadcaster to Suscribe and an Invoker linked
     */
    public static<ParameterType> BroadcasterPackage<ParameterType> GetBroadcaster()
    {
        BroadcasterPackage<ParameterType> pack = new BroadcasterPackage<ParameterType>();
        var b = new Broadcaster<ParameterType>();
        pack.Broadcaster = b;
        pack.Invoker = new Invoker<ParameterType>(b);
        return pack;
    }

}

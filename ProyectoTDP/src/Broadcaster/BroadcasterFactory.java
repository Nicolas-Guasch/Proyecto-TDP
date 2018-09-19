package Broadcaster;

public class BroadcasterFactory
{
    public static<P> BroadcasterPackage<P> GetBroadcaster()
    {
        BroadcasterPackage<P> pack = new BroadcasterPackage<P>();
        pack.Broadcaster = new Broadcaster<P>();
        pack.Invoker = new Invoker<P>(pack.Broadcaster);
        return pack;
    }

}

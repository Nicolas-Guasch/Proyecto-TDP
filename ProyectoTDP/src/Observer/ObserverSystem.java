package Observer;


public class ObserverSystem
{

    private static ObserverSystem instance;
    public static ObserverSystem getInstance(){
        instance = (instance==null) ? new ObserverSystem():instance;
        return instance;
    }

    /**
     * Gets a package with an Broadcaster to Suscribe and an Invoker linked
     */
    public <ParameterType> observerPack<ParameterType> getBroadcaster()
    {
        observerPack<ParameterType> pack = new observerPack<ParameterType>();
        Broadcaster<ParameterType> b = new Broadcaster<ParameterType>();
        pack.Broadcaster = b;
        pack.Invoker = new Invoker<>(b);
        return pack;
    }

}

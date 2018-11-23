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
    public <ParameterType> ObserverPack<ParameterType> getBroadcaster()
    {
        ObserverPack<ParameterType> pack = new ObserverPack<ParameterType>();
        Broadcaster<ParameterType> b = new Broadcaster<ParameterType>();
        pack.Broadcaster = b;
        pack.Invoker = new Invoker<ParameterType>(b);
        return pack;
    }

}

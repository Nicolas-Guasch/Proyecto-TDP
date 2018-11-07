package Engine;

/**
 * Provides a way to access to the engine operations
 */
public class EngineGetter
{
    public static EngineGetter instance;
    public static EngineGetter Instance()
    {
        if(instance==null)
        {
            instance = new EngineGetter();
        }
        return instance;
    }
    private EngineGetter()
    {
        ref = new MyEngine();
    }

    private IEngine ref;


    public IEngine get()
    {
        return ref;
    }
}

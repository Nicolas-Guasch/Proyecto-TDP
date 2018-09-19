package Engine;

public class EngineFactory
{
    public static EngineFactory instance;
    public static EngineFactory Instance()
    {
        if(instance==null)
        {
            instance = new EngineFactory();
        }
        return instance;
    }
    private EngineFactory()
    {
        ref =new Engine();
    }

    private IEngine ref;


    public IEngine get()
    {
        return ref;
    }
}

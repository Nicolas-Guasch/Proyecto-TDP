package Engine;

import Observer.*;

public class TheEngine implements IEngine
{

    private Core core;
    private IBroadcaster<Float> onPhysicsUpdate;
    private IBroadcaster <Object> onUpdate;

    private static IEngine instance;
    public static IEngine getInstance()
    {
        if(instance ==null){
            instance = new TheEngine();
        }
        return instance;
    }

    TheEngine()
    {
        core = Core.getInstance();
        onUpdate = core.getUpdater();
        onPhysicsUpdate = core.getPhysicsUpdater();
    }


    @Override
    public void suscribeToUpdate(GameObject object)
    {
        if(object==null)return;
        //TODO: preguntar si puedo usar clases anonimas
        onUpdate.suscribe(new OperationUpdate(object));
    }



    @Override
    public void suscribeToUpdate(Component component)
    {
        onUpdate.suscribe(new OperationUpdate(component));
    }


    @Override
    public void waitForFrames(Action action, int frames)
    {
        core.waitForFrames(action,frames);
    }

    @Override
    public void start()
    {
        core.Start();
    }


    @Override
    public CorePauser Pauser() {
        return CorePauser.Instance();
    }

    @Override
    public long frameCounter() {
        return core.frameCounter();
    }




}

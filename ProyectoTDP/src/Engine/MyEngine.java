package Engine;

import Observer.*;

class MyEngine implements IEngine
{

    private Core core;
    private IBroadcaster<Float> onPhysicsUpdate;
    private IBroadcaster <Object> onUpdate;

    MyEngine()
    {
        core = Core.getInstance();
        onUpdate = core.getUpdater();
        onPhysicsUpdate = core.getPhysicsUpdater();
    }


    @Override
    public void suscribeToUpdate(GameObject object)
    {
        if(object==null)return;
        onUpdate.suscribe((o)->object.Update());
    }



    @Override
    public void suscribeToUpdate(Component component)
    {
        onUpdate.suscribe((o)->component.update());
    }
    //TODO: guardar une HashMap de los components para desuscribirlos mas fasil luego


    @Override
    public void waitForFrames(Runnable action, int frames)
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

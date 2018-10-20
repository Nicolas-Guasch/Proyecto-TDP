package Engine;

import Broadcaster.*;

class Engine implements IEngine
{

    private Core core;
    private IBroadcaster<Float> onPhysicsUpdate;
    private IBroadcaster <Object> onUpdate;

    Engine()
    {
        core = Core.getInstance();
        onUpdate = core.getUpdater();
        onPhysicsUpdate = core.getPhysicsUpdater();
    }


    @Override
    public void suscribeToUpdate(GameObject object)
    {
        onUpdate.Suscribe((o)->object.Update());
    }

    @Override
    public void suscribeToPhysicsUpdate(GameObject object)
    {
        object.getComponents().forEach((c)->onPhysicsUpdate.Suscribe(c::PhysicsUpdate));
    }

    @Override
    public void suscribeToUpdate(Component component)
    {
        onUpdate.Suscribe((o)->component.update());
    }
    //TODO: guardar une HashMap de los components para desuscribirlos mas fasil luego

    @Override
    public void suscribeToPhysicsUpdate(Component component)
    {
        onPhysicsUpdate.Suscribe(component::PhysicsUpdate);
    }

    @Override
    public void waitForFrames(Runnable action, int frames)
    {
        core.waitForFrames(action,frames);
    }

    @Override
    public void waitForSeconds(Runnable action, float seconds)
    {
        core.waitForSeconds(action,seconds);
    }

    @Override
    public void Start()
    {
        core.Start();
    }

    @Override
    public void Pause()
    {
        core.setPaused(true);
    }

    @Override
    public void Continue() {
        core.setPaused(false);
    }


    @Override
    public void Stop()
    {

    }

    @Override
    public boolean isPaused()
    {
        return core.isPaused();
    }

    @Override
    public CorePauser Pauser() {
        return CorePauser.Instance();
    }

    @Override
    public void unsuscribeFromPhysicsUpdate(Component component)
    {

    }

    @Override
    public void unsuscribeFromUpdate(Component component)
    {

    }

    @Override
    public void unsuscribeFromPhysicsUpdate(GameObject object)
    {

    }

    @Override
    public void unsuscribeFromUpdate(GameObject object)
    {

    }
}

package Engine;

import Broadcaster.*;

import java.util.function.Consumer;

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
    public void SuscribeToUpdate(GameObject object)
    {
        onUpdate.Suscribe((o)->object.Update());
    }

    @Override
    public void SuscribeToPhysicsUpdate(GameObject object)
    {
        object.getComponents().forEach((c)->onPhysicsUpdate.Suscribe(c::PhysicsUpdate));
    }

    @Override
    public void SuscribeToUpdate(Component component)
    {
        onUpdate.Suscribe((o)->component.Update());
    }
    //TODO: guardar une HashMap de los components para desuscribirlos mas fasil luego

    @Override
    public void SuscribeToPhysicsUpdate(Component component)
    {
        onPhysicsUpdate.Suscribe(component::PhysicsUpdate);
    }

    @Override
    public void WaitForFrames(Runnable action, int frames)
    {
        core.waitForFrames(action,frames);
    }

    @Override
    public void WaitForSeconds(Runnable action, float seconds)
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
    public void UnsuscribeFromPhysicsUpdate(Component component)
    {

    }

    @Override
    public void UnsuscribeFromUpdate(Component component)
    {

    }

    @Override
    public void UnsuscribeFromPhysicsUpdate(GameObject object)
    {

    }

    @Override
    public void UnsuscribeFromUpdate(GameObject object)
    {

    }
}

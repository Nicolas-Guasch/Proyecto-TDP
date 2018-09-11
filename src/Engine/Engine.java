package Engine;

import Broadcaster.Broadcaster;

import java.util.function.Consumer;

class Engine implements IEngine
{

    private Core core;
    private Broadcaster<Float> onPhysicsUpdate;
    private Broadcaster <Object> onUpdate;

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
        //object.getComponents().forEach((c)->onUpdate.Suscribe((o)->c.Update()));
    }

    @Override
    public void SuscribeToPhysicsUpdate(GameObject object)
    {
        object.getComponents().forEach((c)->onPhysicsUpdate.Suscribe((t)->c.PhysicsUpdate(t)));
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
        onPhysicsUpdate.Suscribe((t)->component.PhysicsUpdate(t));
    }

    @Override
    public void SuscribeToUpdate(Consumer consumer)
    {
        onUpdate.Suscribe(consumer);
    }

    @Override
    public void SuscribeToPhysicsUpdate(Consumer<Float> consumer)
    {

    }

    @Override
    public void UnsuscribeFromUpdate(Consumer consumer)
    {
        onUpdate.Unsuscribe(consumer);
    }

    @Override
    public void UnsuscribeFromPhysicsUpdate(Consumer<Float> consumer)
    {

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

    }

    @Override
    public void Stop()
    {

    }

    @Override
    public boolean isPaused()
    {
        return false;
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

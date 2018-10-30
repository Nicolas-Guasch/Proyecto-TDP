package InputManager;

import Broadcaster.*;
@Deprecated
public class DummyDiscreteInput extends AbstractDiscreteInput
{

    private boolean active = true;
    private IBroadcaster<Boolean> broadcaster;
    private Invoker<Boolean> invoker;

    public DummyDiscreteInput()
    {
        ObserverPack<Boolean> pack = ObserverSystem.getInstance().GetBroadcaster();
        invoker = pack.Invoker;
        broadcaster = pack.Broadcaster;
    }

    public void Invoke(boolean value)
    {
        if(active)
            invoker.Invoke(value);
    }

    @Override
    public void Destroy() {
        active = false;
        broadcaster.Clean();
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public IBroadcaster<Boolean> OnAction() {
        return broadcaster;
    }
}

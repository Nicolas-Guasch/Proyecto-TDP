package InputManager;

import Broadcaster.*;

public class DummyDiscreteInput extends AbstractDiscreteInput
{

    private boolean active = true;
    private Broadcaster<Boolean> broadcaster;
    private Invoker<Boolean> invoker;

    public DummyDiscreteInput()
    {
        BroadcasterPackage<Boolean> pack = BroadcasterFactory.GetBroadcaster();
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
    public boolean IsActive() {
        return active;
    }

    @Override
    public Broadcaster<Boolean> OnAction() {
        return broadcaster;
    }
}

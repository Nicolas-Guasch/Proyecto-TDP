package InputManager;

import Broadcaster.*;
import Engine.EngineFactory;

public class DiscreteKeyInput extends AbstractDiscreteInput
{


    //bool says if isPressed
    private Broadcaster<Boolean> onAction;
    private Invoker<Boolean> invokerOnAction;

    private AbstractContinueInput related;
    private DummyComponent Dummy;
    private boolean lastStatus;


    public DiscreteKeyInput(String chars)
    {
        BroadcasterPackage<Boolean> pack = BroadcasterFactory.GetBroadcaster();
        onAction = pack.Broadcaster;
        invokerOnAction = pack.Invoker;
        related = new ContinueKeyInput(chars);
        Dummy = new DummyComponent(this::Update);
        EngineFactory.Instance().get().SuscribeToUpdate(Dummy);
    }


    private void Update()
    {
        if(lastStatus != related.Happens())
        {
            invokerOnAction.Invoke(!lastStatus);
            lastStatus = !lastStatus;
        }
    }

    public boolean IsActive()
    {
        return Dummy.isActive();
    }

    public void setActive(boolean active)
    {
        Dummy.setActive(active);
    }


    public void Destroy()
    {
        related.Destroy();
        onAction.Clean();
        EngineFactory.Instance().get().UnsuscribeFromUpdate(Dummy);
    }

    public Broadcaster<Boolean> OnAction()
    {
        return onAction;
    }
}

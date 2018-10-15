package InputManager;

import Broadcaster.*;
import Engine.EngineGetter;

public class DiscreteKeyInput extends AbstractDiscreteInput
{


    //bool says if isPressed
    private IBroadcaster<Boolean> onAction;
    private Invoker<Boolean> invokerOnAction;

    private AbstractContinueInput related;
    private DummyComponent Dummy;
    private boolean lastStatus;


    public DiscreteKeyInput(String chars)
    {
        BroadcasterPackage<Boolean> pack = ObserverSystem.getInstance().GetBroadcaster();
        onAction = pack.Broadcaster;
        invokerOnAction = pack.Invoker;
        related = new ContinueKeyInput(chars);
        Dummy = new DummyComponent(this::Update);
        EngineGetter.Instance().get().suscribeToUpdate(Dummy);
    }


    private void Update()
    {
        if(lastStatus != related.happens())
        {
            invokerOnAction.Invoke(!lastStatus);
            lastStatus = !lastStatus;
        }
    }

    public boolean isActive()
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
        EngineGetter.Instance().get().unsuscribeFromUpdate(Dummy);
    }

    public IBroadcaster<Boolean> OnAction()
    {
        return onAction;
    }
}

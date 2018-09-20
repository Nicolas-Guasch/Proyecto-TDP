package InputManager;

import Broadcaster.*;
import Engine.EngineGetter;

public class DiscreteClick extends AbstractDiscreteInput
{

    //bool says if isPressed
    private IBroadcaster<Boolean> onAction;
    private Invoker<Boolean> invokerOnAction;

    private AbstractContinueInput related;
    private DummyComponent Dummy;
    private boolean lastStatus;

    public DiscreteClick()
    {
        this(0);
    }
    public DiscreteClick(int mouseClick)
    {
        initialize(mouseClick);
    }
    public void initialize(int mouseClick)
    {
        BroadcasterPackage<Boolean> pack = GetBroadcaster.GetBroadcaster();
        onAction = pack.Broadcaster;
        invokerOnAction = pack.Invoker;
        related = new ContinueClick(mouseClick);
        Dummy = new DummyComponent(this::Update);
        EngineGetter.Instance().get().SuscribeToUpdate(Dummy);
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
        EngineGetter.Instance().get().UnsuscribeFromUpdate(Dummy);
    }

    public IBroadcaster<Boolean> OnAction()
    {
        return onAction;
    }
}
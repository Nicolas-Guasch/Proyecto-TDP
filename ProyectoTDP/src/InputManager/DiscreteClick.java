package InputManager;

import Observer.*;
import Engine.EngineGetter;

public class DiscreteClick extends AbstractDiscreteInput
{

    //bool says if isPressed
    private IBroadcaster<Boolean> onAction;
    private Invoker<Boolean> invokerOnAction;

    private AbstractContinueInput related;
    private DummyComponent Dummy;
    private boolean lastStatus;

    public DiscreteClick(int mouseClick)
    {
        initialize(mouseClick);
    }
    public void initialize(int mouseClick)
    {
        observerPack<Boolean> pack = ObserverSystem.getInstance().getBroadcaster();
        onAction = pack.Broadcaster;
        invokerOnAction = pack.Invoker;
        related = new ContinueClick(mouseClick);
        Dummy = new DummyComponent(this::Update);
        EngineGetter.Instance().get().suscribeToUpdate(Dummy);
    }


    private void Update()
    {
        if(lastStatus != related.happens()&& isActive())
        {
            invokerOnAction.invoke(!lastStatus);
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
        setActive(false);
        related.Destroy();
        onAction.clean();
        //EngineGetter.Instance().get().unsuscribeFromUpdate(Dummy);
    }

    public IBroadcaster<Boolean> OnAction()
    {
        return onAction;
    }
}

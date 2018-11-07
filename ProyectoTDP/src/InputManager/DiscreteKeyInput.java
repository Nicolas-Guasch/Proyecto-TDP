package InputManager;

import Observer.*;
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
        observerPack<Boolean> pack = ObserverSystem.getInstance().getBroadcaster();
        onAction = pack.Broadcaster;
        invokerOnAction = pack.Invoker;
        related = new ContinueKeyInput(chars);
        Dummy = new DummyComponent(this::update);
        EngineGetter.Instance().get().suscribeToUpdate(Dummy);
    }


    private void update()
    {
        if(lastStatus != related.happens() && isActive())
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

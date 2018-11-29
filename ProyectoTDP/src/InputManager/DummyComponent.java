package InputManager;

import Engine.Action;
import Engine.Component;

public final class DummyComponent extends Component
{

    private Action onUpdate;

    DummyComponent(Action onUpdate)
    {
        this.onUpdate = onUpdate;
    }

    @Override
    public void update()
    {
        if(onUpdate!=null)
            onUpdate.invoke();
    }

    @Override
    public void onDestroy()
    {
        setActive(false);
        onUpdate = null;
    }
}

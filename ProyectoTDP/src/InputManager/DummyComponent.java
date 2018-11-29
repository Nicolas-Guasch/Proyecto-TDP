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
        onUpdate.invoke();
    }

    @Override
    public void OnDestroy() {
        onUpdate = null;
    }
}

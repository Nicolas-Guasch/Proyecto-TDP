package InputManager;

import Engine.Component;

public final class DummyComponent extends Component
{

    private Runnable onUpdate;

    DummyComponent(Runnable onUpdate)
    {
        this.onUpdate = onUpdate;
    }

    @Override
    public void Update() {
        onUpdate.run();
    }
}

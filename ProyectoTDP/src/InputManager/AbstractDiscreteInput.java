package InputManager;

import Observer.*;
import Engine.Components.IActivable;

public abstract class AbstractDiscreteInput implements IActivable
{
    public abstract void Destroy();
    public abstract IBroadcaster<Boolean> OnAction();
}


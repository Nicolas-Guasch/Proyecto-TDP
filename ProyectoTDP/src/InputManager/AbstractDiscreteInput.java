package InputManager;

import Broadcaster.*;

public abstract class AbstractDiscreteInput
{
    public abstract void Destroy();
    public abstract void setActive(boolean active);
    public abstract boolean IsActive();
    public abstract Broadcaster<Boolean> OnAction();
}


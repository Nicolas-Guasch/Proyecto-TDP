package InputManager;

import ADTs.IVector2;
import ADTs.Vector2;

public abstract class AbstractDirectionalInput
{
    public abstract  void Destroy() ;
    public abstract IVector2 Direction();
}


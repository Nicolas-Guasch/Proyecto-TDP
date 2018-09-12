package InputManager;

import Engine.Components.Transform;
import Engine.Vector2;

public class PointToPointDirectional extends AbstractDirectionalInput
{

    private Transform source,destiny;
    public PointToPointDirectional(Transform source, Transform destiny)
    {
        this.source=source;
        this.destiny = destiny;
    }

    @Override
    public void Destroy() {
        
    }

    @Override
    public Vector2 Direction() {
        return destiny.position().minus(source.position()).versor();
    }
}

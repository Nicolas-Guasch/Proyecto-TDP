package AIs;

import Engine.Component;
import Engine.Components.ITransform;

/**
 * Util Behaviour for many entities
 * This will always turn the Top Looking to the target
 */
public class LookTarget extends Component
{
    private ITransform target;

    public LookTarget(ITransform playerITransform) {
        this.target = playerITransform;
    }

    @Override
    public void update()
    {
        transform().setTop(target.position().sub(transform().position()));
    }
}

package Entities.Behaviours;

import Engine.Component;
import Engine.Components.Transform;

/**
 * Util Behaviour for many entities
 * This will always turn the Top Looking to the target
 */
public class LookTarget extends Component
{
    private Transform target;

    public LookTarget(Transform playerTransform) {
        this.target = playerTransform;
    }

    @Override
    public void Update()
    {
        transform().setTop(target.position().minus(transform().position()));
    }
}

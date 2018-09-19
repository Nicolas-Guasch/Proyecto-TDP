package Entities.Behaviours;

import Engine.Component;
import Engine.Components.Transform;

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

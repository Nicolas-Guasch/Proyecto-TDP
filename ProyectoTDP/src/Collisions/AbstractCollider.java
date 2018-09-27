package Collisions;

import Engine.Component;
import Entities.Entity;

public abstract class AbstractCollider<ColliderType extends AbstractCollider<ColliderType>> extends Component
{

    protected Entity entity;
    public AbstractCollider(Entity entity)
    {
        this.entity = entity;
    }
    public Entity getEntity()
    {
        return entity;
    }

    public abstract CollisionData CheckCollision(ColliderType c );

}

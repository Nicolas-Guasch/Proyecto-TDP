package Collisions;

import Engine.Vector2;
import Entities.Entity;
import RenderingSystem.SpriteData;

enum ColliderShape{
    Circle,
    Rectangle,
}


public class ColliderProvider
{
    private static ColliderProvider instance;

    public static ColliderProvider getInstance()
    {
        instance = instance == null ? new ColliderProvider() : instance;
        return instance;
    }

    public AbstractCollider<CircleCollider> getCircleCollider(float radius, Entity entity)
    {
        return new CircleCollider(radius,entity);
    }
    public AbstractCollider<CircleCollider> getCircleCollider(Vector2 dimensions, Entity entity)
    {
        return new CircleCollider(dimensions,entity);
    }
    public <ColliderType extends AbstractCollider<CircleCollider>> ColliderType getCircleCollider(SpriteData spriteData, Entity entity)
    {
        ColliderType col = (ColliderType) new CircleCollider(spriteData,entity);
        return col;
    }

    public AbstractCollider<RectangleCollider> getRectangleCollider(Vector2 dimensions, Entity entity)
    {
        return new RectangleCollider(dimensions,entity);
    }

}

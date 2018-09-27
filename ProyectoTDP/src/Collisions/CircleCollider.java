package Collisions;

import Engine.Components.Transform;
import Engine.Vector2;
import RenderingSystem.SpriteData;
import Entities.Entity;


public class CircleCollider extends AbstractCollider<CircleCollider>
{

    private Transform transform;

    private float ratio;

    public CircleCollider(float ratio, Entity entity)
    {
        super(entity);
        this.ratio = ratio;
    }

    public CircleCollider(Vector2 dimensions, Entity entity)
    {
        this(Math.max(dimensions.x(),dimensions.y())/2,entity);
    }
    public CircleCollider(SpriteData spriteData, Entity entity)
    {
        this(new Vector2(spriteData.getWidth(),spriteData.getHeight()),entity);
    }

    @Override
    public void Start() {
        transform = getEntity().getReferenced().getTransform();
    }

    public CollisionData CheckCollision(CircleCollider c )
    {
        if(c.transform.position().distanceTo(transform.position()) < c.ratio + ratio)
        {
            return new CollisionData(entity,c.entity,c.transform().position().sum(transform.position()).prod(.5f));
        }
        return null;
    }

    public CollisionData CheckCollision(RectangleCollider c){
        for(Vector2 v:c.vertices()){
            if(v.distanceTo(transform.position()) < ratio)
            {
                return new CollisionData(entity,c.entity,v);
            }
        }
        return null;
    }

}


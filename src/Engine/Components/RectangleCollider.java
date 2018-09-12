package Engine.Components;

import Engine.Vector2;
import RenderingSystem.SpriteData;
import Entities.Entity;


public class RectangleCollider extends AbstractCollider<RectangleCollider>
{
    private Vector2 dimensions;
    private Transform transform;

    public RectangleCollider(Vector2 dimensions, Entity entity)
    {
        super(entity);
        this.dimensions = dimensions;
    }
    public RectangleCollider(SpriteData spriteData, Entity entity)
    {
        this(new Vector2(spriteData.getWidth(),spriteData.getHeight()),entity);
    }

    @Override
    public void Start() {
        transform = gameObject().getTransform();
    }

    //TODO: hay que ver que pasa cuando se gira el tirito, cambiar algoritmo

    private Vector2 bottomLeft()
    { //pos+dim/2
        return transform.position().minus(dimensions.div(2));
    }
    private Vector2 topRight()
    {//pos-dim/2

        return transform.position().sum(dimensions.div(2));
    }



    public CollisionData<RectangleCollider> CheckCollision(RectangleCollider c )
    {
        CollisionData data = null;
        Vector2 lw =
                new Vector2(Math.max(bottomLeft().x(),c.bottomLeft().x()),
                        Math.max(bottomLeft().y(),c.bottomLeft().y()));
        Vector2 up =
                new Vector2(Math.min(topRight().x(),c.topRight().x()),
                        Math.min(topRight().y(),c.topRight().y()));
        if(lw.x() <= up.x() && lw.y() <= up.y() ) // si colisiona:
        {
            data = new CollisionData<>(this, c, lw.sum(up).div(2));// (lw+up)/2
        }
        return data;
    }


}

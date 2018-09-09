package Engine.Components;

import Engine.Component;
import Engine.Vector2;


public class RectangleCollider extends AbstractCollider<RectangleCollider> // Transform position must be in the middle
{
    private Vector2 Dimensions;
    private Transform transform;



    public RectangleCollider(Vector2 dimensions)
    {
        Dimensions = dimensions;
    }

    @Override
    public void Start() {
        transform = gameObject().getTransform();
    }

    //TODO: hay que ver que pasa cuando se gira el tirito, cambiar algoritmo

    private Vector2 bottomLeft()
    { //pos+dim/2
        return transform.getPosition().minus(Dimensions.div(2));
    }
    private Vector2 topRight()
    {//pos-dim/2

        return transform.getPosition().sum(Dimensions.div(2));
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
            data = new CollisionData<RectangleCollider>(this, c, lw.sum(up).div(2));// (lw+up)/2
        }
        return data;
    }


}

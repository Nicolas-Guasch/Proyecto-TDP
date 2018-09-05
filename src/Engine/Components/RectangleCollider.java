package Engine.Components;

import Engine.Component;


public class RectangleCollider extends Component // Transform position must be in the middle
{
    private Vector2 Dimensions;

    public RectangleCollider(Vector2 dimensions)
    {
        Dimensions = dimensions;
    }

    private Vector2 bottomLeft()
    { //pos+dim/2
        return gameObject().getTransform().getPosition().minus(Dimensions.div(2));
    }
    private Vector2 topRight()
    {//pos-dim/2
        return gameObject().getTransform().getPosition().sum(Dimensions.div(2));
    }

    public CollisionData CheckCollision(RectangleCollider c )
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
            data = new CollisionData(this, c, lw.sum(up).div(2));// (lw+up)/2
        }
        return data;
    }


}

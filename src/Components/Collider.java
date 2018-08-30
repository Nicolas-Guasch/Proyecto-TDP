package Components;

import Engine.CollisionData;
import Engine.MonoBehaviour;
import Engine.Vector2;


public class Collider extends MonoBehaviour<Collider> // Transform position must be in the middle
{
    private Vector2 Dimensions;

    private Collider(Vector2 dimensions) {
        super();
        Dimensions = dimensions;
    }

    private Vector2 bottomLeft()
    { //pos+dim/2
        return transform().getPosition().minus(Dimensions.div(2));
    }
    private Vector2 topRight()
    {//pos-dim/2
        return transform().getPosition().sum(Dimensions.div(2));
    }

    public CollisionData CheckCollision(Collider c )
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
            data = new CollisionData(this,c,lw.sum(up).div(2));// (lw+up)/2
        }
        return data;
    }




    @Override
    public String save() {
        return Dimensions +"";
    }
    @Override
    public void load(String data) {
        Dimensions = Vector2.ORIGIN().load(data);
    }

    @Override
    public void copy(Collider s)
    {
        Dimensions = s.Dimensions;
    }

    @Override
    public Collider clone() {
        return new Collider(Dimensions);
    }
}

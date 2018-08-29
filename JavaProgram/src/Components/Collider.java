package Components;

import Engine.CollisionData;
import Engine.MonoBehaviour;
import Engine.Vector2;


public class Collider extends MonoBehaviour
{
    private Vector2 bottomLeft;
    private Vector2 topRight;

    public CollisionData CheckCollision(Collider c )
    {
        CollisionData data = null;
        Vector2 lw =
                new Vector2(Math.max(bottomLeft.x(),c.bottomLeft.x()),
                        Math.max(bottomLeft.y(),c.bottomLeft.y()));
        Vector2 up =
                new Vector2(Math.min(topRight.x(),c.topRight.x()),
                        Math.min(topRight.y(),c.topRight.y()));
        if(lw.x() <= up.x() && lw.y() <= up.y() ) // si colisiona:
        {
            data = new CollisionData();
            data.ContactPoint = lw.sum(up).div(2); //TODO: chequear esto <----
            data.Mine = this;
            data.Their = c;
        }
        return data;
    }
}

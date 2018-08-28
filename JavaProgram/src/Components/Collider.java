package Components;

import Engine.GameObject;
import Engine.MonoBehaviour;
import Engine.Vector2;


public abstract class Collider extends MonoBehaviour
{
    private Vector2 bottomLeft;
    private Vector2 topRight;

    public boolean CheckCollision(GameObject other)
    {
        boolean ret = false;
        Collider c = other.GetComponent(Collider.class);
        if(other.GetComponent(Collider.class)!= null)
        {
            Vector2 lw =
                    new Vector2(Math.max(bottomLeft.x(),c.bottomLeft.x()),
                            Math.max(bottomLeft.y(),c.bottomLeft.y()));
            Vector2 up =
                    new Vector2(Math.min(topRight.x(),c.topRight.x()),
                            Math.min(topRight.y(),c.topRight.y()));
            ret = lw.x() <= up.x() && lw.y() <= up.y() ;
        }
        return ret;
    }

}

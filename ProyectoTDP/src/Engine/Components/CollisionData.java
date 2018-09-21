package Engine.Components;

import Engine.Vector2;
import Entities.Entity;

public class CollisionData
{
    private Entity mine;
    private Entity their;
    private Vector2 contactPoint;

    public Entity Mine (){return mine;}
    public Entity Their (){return their;}
    public Vector2 ContactPoint(){return contactPoint;}

    public CollisionData(Entity mine, Entity their , Vector2 contactPoint)
    {
        this.mine = mine;
        this.their = their;
        this.contactPoint = contactPoint;
    }
    public CollisionData invert()
    {
        return new CollisionData(their, mine, contactPoint);
    }

}

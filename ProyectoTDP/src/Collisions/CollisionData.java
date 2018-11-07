package Collisions;

import ADTs.Vector2;
import Entities.Entity;

public class CollisionData
{
    private Entity mine;
    private Entity their;
    private Vector2 contactPoint;

    public Entity mine(){return mine;}
    public Entity their(){return their;}
    public Vector2 contactPoint(){return contactPoint;}

    public CollisionData(Entity mine, Entity their , Vector2 contactPoint)
    {
        this.mine = mine;
        this.their = their;
        this.contactPoint = contactPoint;
    }

}

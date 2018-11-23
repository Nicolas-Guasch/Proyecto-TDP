package Collisions;

import ADTs.IVector2;
import ADTs.Vector2;
import Entities.Entity;

public class CollisionData
{
    private Entity mine;
    private Entity their;
    private IVector2 contactPoint;

    public Entity mine(){return mine;}
    public Entity their(){return their;}
    public IVector2 contactPoint(){return contactPoint;}

    public CollisionData(Entity mine, Entity their , IVector2 contactPoint)
    {
        this.mine = mine;
        this.their = their;
        this.contactPoint = contactPoint;
    }

}

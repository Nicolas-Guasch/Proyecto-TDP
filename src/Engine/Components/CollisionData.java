package Engine.Components;

import Engine.Vector2;

public class CollisionData
{
    private RectangleCollider mine;
    private RectangleCollider their;
    private Vector2 contactPoint;

    public RectangleCollider Mine () {return mine;}
    public RectangleCollider Their () {return their;}
    public Vector2 ContactPoint(){return contactPoint;}

    public CollisionData(RectangleCollider mine, RectangleCollider their , Vector2 contactPoint)
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

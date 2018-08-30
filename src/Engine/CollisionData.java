package Engine;

import Components.Collider;

public class CollisionData
{
    private Collider mine;
    private Collider their;
    private Vector2 contactPoint;

    public Collider Mine () {return mine;}
    public Collider Their () {return their;}
    public Vector2 ContactPoint(){return contactPoint;}

    public CollisionData(Collider mine, Collider their , Vector2 contactPoint)
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

package Engine.Components;

import Engine.Vector2;

public class CollisionData<ColliderType extends AbstractCollider<ColliderType>>
{
    private ColliderType mine;
    private ColliderType their;
    private Vector2 contactPoint;

    public ColliderType Mine () {return mine;}
    public ColliderType Their () {return their;}
    public Vector2 ContactPoint(){return contactPoint;}

    public CollisionData(ColliderType mine, ColliderType their , Vector2 contactPoint)
    {
        this.mine = mine;
        this.their = their;
        this.contactPoint = contactPoint;
    }
    public CollisionData invert()
    {
        return new CollisionData<ColliderType>(their, mine, contactPoint);
    }

}

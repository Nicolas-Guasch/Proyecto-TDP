package Engine.Components;

import Engine.Component;

public abstract class AbstractCollider<ColliderType extends AbstractCollider<ColliderType>> extends Component implements Hazardous
{
    public abstract CollisionData<ColliderType> CheckCollision(ColliderType c );

    private boolean hazardous = false;
    private float damage;

    public final boolean isHazardous() {
        return hazardous;
    }
    public final void setHazardous(boolean hazardous)
    {
        this.hazardous = hazardous;
    }
    @Override
    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}

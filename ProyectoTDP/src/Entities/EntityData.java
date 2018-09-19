package Entities;

public final class EntityData
{
    private float health;
    private float damage;
    private float shield;

    public EntityData(float health, float damage, float shield)
    {
        this.health = health;
        this.damage = damage;
        this.shield = shield;
    }

    public EntityData clone()
    {
        return new EntityData(health,damage,shield);
    }

    public float getShield() {
        return shield;
    }

    public float getDamage() {
        return damage;
    }

    public float getHealth() {
        return health;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}

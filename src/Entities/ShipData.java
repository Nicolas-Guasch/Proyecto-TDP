package Entities;

public class ShipData
{
    private float shield;
    private float health;
    private float damage;

    public ShipData(float shield, float health, float damage) {
        this.shield = shield;
        this.health = health;
        this.damage = damage;
    }

    public float shield() {
        return shield;
    }

    public float healt() {
        return health;
    }

    public float damage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }
}

package Entities;


import Engine.GameObject;

public class Ship implements Entity
{
    private GameObject Referenced;
    private float collisionDamage;

    private Weapon weapon;
    private ShipData data;


    public Ship()
    {

    }


    public GameObject getReferenced() {
        return Referenced;
    }

    public void setReferenced(GameObject referenced) {
        Referenced = referenced;
    }

    public AbstractShooter getShooter() {
        return Shooter;
    }

    public float hazard() {
        return collisionDamage;
    }

    public void setDamage(float damage){
        this.collisionDamage = damage;
    }


    public Weapon weapon() {
        return weapon;

    }
}

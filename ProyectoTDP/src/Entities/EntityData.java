package Entities;

import Observer.observerPack;
import Observer.IBroadcaster;
import Observer.Invoker;
import Observer.ObserverSystem;

public final class EntityData
{

    private IBroadcaster<Float> HealthData;
    private Invoker<Float> HealthDataInvoker;


    private float health;
    private float damage;
    private float shield;

    private float initialHealth;

    public EntityData(float health, float damage, float shield)
    {
        assert shield >=0 && shield <=1;
        this.health = health;
        this.damage = damage;
        this.shield = shield;
        this.initialHealth = health;

        observerPack<Float> pack = ObserverSystem.getInstance().getBroadcaster();
        HealthData = pack.Broadcaster;
        HealthDataInvoker = pack.Invoker;
    }

    public IBroadcaster<Float> getHealthObservable(){
        return HealthData;
    }

    public static EntityData WithEqualsValues(float v) {
        float shield = v;
        shield = shield<0?0:shield;
        shield = shield>1?1:shield;
        return new EntityData(v,v,shield);
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
        assert shield >=0 && shield <=1;
        this.shield = shield;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public void setHealth(float health) {
        this.health = health;
        if(this.health >initialHealth)
        {
            this.health = initialHealth;
        }
        HealthDataInvoker.invoke(this.health);
    }



    public void takeDamage(float damage) {
        health -= damage*(1-shield);
        HealthDataInvoker.invoke(health);
    }

    public float getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(float initialHealth) {
        this.initialHealth = initialHealth;
    }
}

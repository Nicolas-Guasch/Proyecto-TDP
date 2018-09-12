package Entities;

import Engine.GameObject;

public class Bullet implements Entity
{
    private float hazard;
    private GameObject referenced;

    public Bullet(float hazard, GameObject referenced){
        this.hazard = hazard;
        this.referenced = referenced;
    }
    @Override
    public float hazard() {
        return hazard;
    }

    public GameObject referenced() {
        return referenced;
    }
}

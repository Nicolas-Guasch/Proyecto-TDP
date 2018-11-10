package Scripts;

import ADTs.Vector2;
import Engine.Components.Transform;

public class PushData{
    private final Transform target;
    private int counter;
    private Vector2 velocity;
    private float dismish;

    PushData(int counter, Vector2 velocity, float dismish, Transform target) {
        this.counter = counter;
        this.velocity = velocity;
        this.dismish = dismish;
        this.target = target;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public int getCounter() {
        return counter;
    }

    public float getDismish() {
        return dismish;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setDismish(float dismish) {
        this.dismish = dismish;
    }

    public Transform getTarget() {
        return target;
    }

    public void dec() {
        counter--;
    }
}

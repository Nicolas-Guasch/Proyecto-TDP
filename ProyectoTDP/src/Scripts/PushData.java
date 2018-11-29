package Scripts;

import ADTs.IVector2;
import Engine.Components.ITransform;

public class PushData{
    private final ITransform target;
    private int counter;
    private IVector2 velocity;
    private float dismish;

    PushData(int counter, IVector2 velocity, float dismish, ITransform target) {
        this.counter = counter;
        this.velocity = velocity;
        this.dismish = dismish;
        this.target = target;
    }

    public IVector2 getVelocity() {
        return velocity;
    }

    public int getCounter() {
        return counter;
    }

    public float getDismish() {
        return dismish;
    }

    public void setVelocity(IVector2 velocity) {
        this.velocity = velocity;
    }

    public void setDismish(float dismish) {
        this.dismish = dismish;
    }

    public ITransform getTarget() {
        return target;
    }

    public void dec() {
        counter--;
    }
}

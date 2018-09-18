package Engine.Components;

import Engine.Component;
import Engine.EngineFactory;
import Engine.Vector2;

public final class Transform extends Component
{
    private Vector2 position;
    private Vector2 top = Vector2.UP();
    private float Zcomponent = 0;
    // Top of the sprite always look this way


    public Transform()
    {
        this(Vector2.ORIGIN());
    }

    private Transform(Vector2 position)
        {

            this.position = position;
        }

    public Vector2 position() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void MoveTowards(Vector2 direction){
        position = position.sum(direction);
    }


    public Vector2 top() {
        return top;
    }

    public void setTop(Vector2 top) {
        this.top = top.versor();
    }

    public void resetTop(){
        this.top = Vector2.UP();
    }
    public void SetFromPrototype(Transform prototype)
    {
        this.position = prototype.position;
        this.top = prototype.top;
        this.Zcomponent = prototype.Zcomponent;
    }
    public float getZcomponent() {
        return Zcomponent;
    }

    public void setZcomponent(float zcomponent) {
        Zcomponent = zcomponent;
    }

    public Vector2 top(float large)
    {
        return top().prod(large);
    }

    public void rotate(float angle)
    {
        top = top.rotate(angle);
    }
    public void rotateUnary(float angle)
    {
        top = top.rotateUnary(angle);
    }

    public void DoMove(Vector2 vector2, int inframes) {
        //TODO: hacer tipo tween
        EngineFactory.Instance().get().WaitForFrames(()->
        {
            setPosition(vector2);
        },inframes);
    }
}


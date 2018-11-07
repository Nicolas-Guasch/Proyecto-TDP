package Engine.Components;

import Engine.Component;
import Engine.EngineGetter;
import ADTs.Vector2;
import ADTs.Vector3;


/**
 * Implements the position and rotation of and 2D object, and provides
 * the Z component sorting data
 */
public final class Transform extends Component
{
    private Vector2 position;
    private Vector2 top = Vector2.UP(); // Top of the sprite always look this way
    private float Zcomponent = 0;
    private int lifetime = 0;


    @Override
    public void update() {
        lifetime++;
    }

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

    public void moveTowards(Vector2 direction){
        position = position.sum(direction);
    }


    public Vector2 top() {
        return top;
    }

    public void setTop(Vector2 top) {
        this.top = top.norma();
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
        top = top.rot(angle);
    }
    public void rotateUnary(float angle)
    {
        top = top.rotateUnary(angle);
    }



    public void setPosition(Vector3 v) {
        setPosition(v.xy());
        setZcomponent(v.z());
    }


    public void DoMove(Vector2 vector2, int inframes) {
        //TODO: hacer tipo tween
        EngineGetter.Instance().get().waitForFrames(()->
        {
            setPosition(vector2);
        },inframes);
    }


    public Vector3 position3() {
        return new Vector3(position.x(),position.y(),Zcomponent);
    }

    public int getLifetime() {
        return lifetime;
    }
}


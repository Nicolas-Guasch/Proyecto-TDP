package Engine.Components;

import ADTs.IVector2;
import Engine.Component;

import ADTs.Vector2;
import ADTs.Vector3;
import Engine.TheEngine;


/**
 * Implements the position and rotation of and 2D object, and provides
 * the Z component sorting data
 */
public final class Transform extends Component implements ITransform {
    private IVector2 position;
    private IVector2 top = Vector2.UP(); // Top of the sprite always look this way
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

    private Transform(IVector2 position)
        {

            this.position = position;
        }

    @Override
    public IVector2 position() {
        return position;
    }

    @Override
    public void setPosition(IVector2 position) {
        this.position = position;
    }

    @Override
    public void moveTowards(IVector2 direction){
        position = position.sum(direction);
    }


    @Override
    public IVector2 top() {
        return top;
    }

    @Override
    public void setTop(IVector2 top) {
        this.top = top.norma();
    }


    public void setFromPrototype(ITransform prototype)
    {
        this.position = prototype.position();
        this.top = prototype.top();
        this.Zcomponent = prototype.getZcomponent();
    }
    @Override
    public float getZcomponent() {
        return Zcomponent;
    }

    @Override
    public void setZcomponent(float zcomponent) {
        Zcomponent = zcomponent;
    }

    @Override
    public IVector2 top(float large)
    {
        return top().prod(large);
    }

    @Override
    public void rotate(float angle)
    {
        top = top.rot(angle);
    }
    @Override
    public void rotateUnary(float angle)
    {
        top = top.rotateUnary(angle);
    }



    @Override
    public void setPosition(Vector3 v) {
        setPosition(v.xy());
        setZcomponent(v.z());
    }


    @Override
    public void DoMove(IVector2 vector2, int inframes) {
        //TODO: hacer tipo tween
        TheEngine.getInstance().waitForFrames(()->
        {
            setPosition(vector2);
        },inframes);
    }


    @Override
    public Vector3 position3() {
        return new Vector3(position.x(),position.y(),Zcomponent);
    }

    @Override
    public int getLifetime() {
        return lifetime;
    }
}


package ADTs;

import java.awt.*;

/**
 * a Vector in R2 an his operations
 */
public final class Vector2 implements IVector2 {
    public static IVector2 ORIGIN(){return new Vector2(0,0);}
    public static IVector2 UP (){   return new Vector2(0,1);}
    public static IVector2 DOWN (){ return new Vector2(0,-1);}
    public static IVector2 LEFT (){ return new Vector2(-1,0);}
    public static IVector2 RIGHT (){return new Vector2(1,0);}

    public static IVector2 UP (float x){   return new Vector2(0,x);}
    public static IVector2 DOWN (float x){ return new Vector2(0,-x);}
    public static IVector2 LEFT (float x){ return new Vector2(-x,0);}
    public static IVector2 RIGHT (float x){return new Vector2(x,0);}

    private static final float Epsilon = 0.01f;

    private float x,y;

    public static IVector2 random()
    {
        float x = (float)Math.random()*2.0f-1.0f;
        float y = (float)Math.sqrt(1-x*x);
        if(Math.random()<0.5)y*=-1;
        return new Vector2(x,y);
    }

    public static IVector2 random(float large)
    {
        return random().withLength(large);
    }



    @Override
    public float x(){return x;}
    @Override
    public float y(){return y;}

    public Vector2(double xx, double yy){
        this((float)xx,(float)yy);
    }

    public static IVector2 get(double xx, double yy){
        return new Vector2(xx,yy);
    }

    public Vector2(float x , float y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public IVector2 sum(IVector2 other)
    {
        return new Vector2(x+other.x() ,  y+other.y());
    }

    @Override
    public IVector2 sub(IVector2 other)
    {
        return new Vector2(x-other.x (),  y-other.y());
    }

    @Override
    public IVector2 prod(float real){
        return new Vector2(x*real,y*real);
    }

    @Override
    public float scalarProd(IVector2 other){
        return x*other.x()+y*other.y();
    }

    @Override
    public float length()
    {
        return (float)Math.sqrt(x*x + y*y);
    }

    @Override
    public float lengthSq(){ return x*x+y*y;}

    @Override
    public IVector2 div(float real)
    {
        if(real == 0)
            throw new EngineException("Never divided by zero");
        return new Vector2(x/real,y/real);
    }

    @Override
    public IVector2 norma()
    {
        if(length()<0.001f)
            return ORIGIN();
        if(length()==1){
            return this;
        }
        return this.div(length());
    }


    @Override
    public IVector2 rot(float angle)
    {
        IVector2 self = this;
        return new Vector2((float)(self.x() * Math.cos(angle)  - self.y() * Math.sin(angle)) ,(float) (self.x() * Math.sin(angle) + self.y() * Math.cos(angle)));
    }
    @Override
    public float getAngle(IVector2 other)
    {
        return (float) Math.atan2( x*other.y() - y*other.x(), x*other.x() + y*other.y() );
    }

    /**
     *
     * @param angle range[-1,1] -1
     * in case you want to perform a counter-clockwise rotation put an -1,
     * but if you want to rot clockwise put positive values
     * @return new vector rotated
     */
    @Override
    public IVector2 rotateUnary(float angle)
    {
        IVector2 self = this;
        float a = (float) Math.PI * angle *2;
        return new Vector2((float)(self.x() * Math.cos(a)  - self.y() * Math.sin(a)) ,(float) (self.x() * Math.sin(a) + self.y() * Math.cos(a)));
    }

    @Override
    public float getUnaryAngle(IVector2 other)
    {
        return (float) (Math.atan2( x*other.y() - y*other.x(), x*other.x() + y*other.y() )/(Math.PI*2));
    }


    @Override
    public boolean near(IVector2 other)
    {
        return other.sub(this).length()< Epsilon;
    }

    @Override
    public boolean equals(IVector2 other)
    {
        return other.x() == x && other.y() == y;
    }

    @Override
    public IVector2 swapped()
    {
        return new Vector2(y,x);
    }


    @Override
    public IVector2 mirrorY()
    {
        return new Vector2(-x,y);
    }

    @Override
    public IVector2 mirrorX()
    {
        return new Vector2(x,-y);
    }

    @Override
    public IVector2 withLength(float speed)
    {
        return norma().prod(speed);
    }

    @Override
    public IVector2 right() {
        return new Vector2(y,-x);
    }
    @Override
    public IVector2 right(float length) {
        return new Vector2(y,-x).withLength(length);
    }

    @Override
    public float distanceTo(IVector2 other)
    {
        return sub(other).length();
    }

    @Override
    public IVector2 half() {
        return new Vector2(x/2,y/2);
    }

    @Override
    public boolean over(IVector2 v) {
        return y > v.y();
    }

    @Override
    public boolean under(IVector2 v)
    {
        return y<v.y();
    }

    @Override
    public boolean onLeft(IVector2 v)
    {
        return x<v.x();
    }

    @Override
    public boolean onRight(IVector2 v)
    {
        return x>v.x();
    }

    @Override
    public Vector3 v3(float z) {
        return new Vector3(x,y,z);
    }

    @Override
    public Vector3 v3() {
        return new Vector3(x,y,0);
    }

    @Override
    public IVector2 withMaxLength(float max) {
        if(length()<max){
            return this;
        }
        else{
            return withLength(max);
        }
    }

    @Override
    public Dimension toDimension() {
        return new Dimension((int)x,(int)y);
    }

    /*inner class*/
    private class EngineException extends RuntimeException {
        EngineException(String msg) {
            super(msg);
        }
    }

    public String toString()
    {
        return String.format("(%f,%f)",x,y);
    }
}

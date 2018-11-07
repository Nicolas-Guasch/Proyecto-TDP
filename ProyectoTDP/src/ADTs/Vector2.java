package ADTs;

import java.awt.*;

/**
 * a Vector in R2 an his operations
 */
public final class Vector2
{
    public static Vector2 ORIGIN(){return new Vector2(0,0);}
    public static Vector2 UP (){   return new Vector2(0,1);}
    public static Vector2 DOWN (){ return new Vector2(0,-1);}
    public static Vector2 LEFT (){ return new Vector2(-1,0);}
    public static Vector2 RIGHT (){return new Vector2(1,0);}

    public static Vector2 UP (float x){   return new Vector2(0,x);}
    public static Vector2 DOWN (float x){ return new Vector2(0,-x);}
    public static Vector2 LEFT (float x){ return new Vector2(-x,0);}
    public static Vector2 RIGHT (float x){return new Vector2(x,0);}

    private static final float Epsilon = 0.01f;

    private float x,y;

    public static Vector2 random()
    {
        float x = (float)Math.random()*2.0f-1.0f;
        float y = (float)Math.sqrt(1-x*x);
        if(Math.random()<0.5)y*=-1;
        return new Vector2(x,y);
    }

    public static Vector2 random(float large)
    {
        return random().withLength(large);
    }



    public float x(){return x;}
    public float y(){return y;}

    public Vector2(double xx, double yy){
        this((float)xx,(float)yy);
    }

    public Vector2(float x , float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2 sum(Vector2 other)
    {
        return new Vector2(x+other.x ,  y+other.y);
    }

    public Vector2 sub(Vector2 other)
    {
        return new Vector2(x-other.x ,  y-other.y);
    }

    public Vector2 prod(float real){
        return new Vector2(x*real,y*real);
    }

    public float scalarProd(Vector2 other){
        return x*other.x()+y*other.y();
    }

    public float length()
    {
        return (float)Math.sqrt(x*x + y*y);
    }

    public float lengthSq(){ return x*x+y*y;}

    public Vector2 div(float real)
    {
        if(real == 0)
            throw new EngineException("Never divided by zero");
        return new Vector2(x/real,y/real);
    }

    public Vector2 norma()
    {
        if(length()<0.001f)
            return ORIGIN();
        if(length()==1){
            return this;
        }
        return this.div(length());
    }


    public Vector2 rot(float angle)
    {
        Vector2 self = this;
        return new Vector2((float)(self.x * Math.cos(angle)  - self.y * Math.sin(angle)) ,(float) (self.x * Math.sin(angle) + self.y * Math.cos(angle)));
    }
    public float getAngle(Vector2 other)
    {
        return (float) Math.atan2( x*other.y - y*other.x, x*other.x + y*other.y );
    }

    /**
     *
     * @param angle range[-1,1] -1
     * in case you want to perform a counter-clockwise rotation put an -1,
     * but if you want to rot clockwise put positive values
     * @return new vector rotated
     */
    public Vector2 rotateUnary(float angle)
    {
        Vector2 self = this;
        float a = (float) Math.PI * angle *2;
        return new Vector2((float)(self.x * Math.cos(a)  - self.y * Math.sin(a)) ,(float) (self.x * Math.sin(a) + self.y * Math.cos(a)));
    }

    public float getUnaryAngle(Vector2 other)
    {
        return (float) (Math.atan2( x*other.y - y*other.x, x*other.x + y*other.y )/(Math.PI*2));
    }


    public boolean near(Vector2 other)
    {
        return other.sub(this).length()< Epsilon;
    }

    public boolean equals(Vector2 other)
    {
        return other.x() == x && other.y() == y;
    }

    public Vector2 swapped()
    {
        return new Vector2(y,x);
    }


    public Vector2 mirrorY()
    {
        return new Vector2(-x,y);
    }

    public Vector2 mirrorX()
    {
        return new Vector2(x,-y);
    }

    public Vector2 withLength(float speed)
    {
        return norma().prod(speed);
    }

    public Vector2 right() {
        return new Vector2(y,-x);
    }
    public Vector2 right(float length) {
        return new Vector2(y,-x).withLength(length);
    }

    public float distanceTo(Vector2 other)
    {
        return sub(other).length();
    }

    public Vector2 half() {
        return new Vector2(x/2,y/2);
    }

    public boolean over(Vector2 v) {
        return y > v.y;
    }

    public boolean under(Vector2 v)
    {
        return y<v.y();
    }

    public boolean onLeft(Vector2 v)
    {
        return x<v.x;
    }

    public boolean onRight(Vector2 v)
    {
        return x>v.x;
    }

    public Vector3 v3(float z) {
        return new Vector3(x,y,z);
    }

    public Vector3 v3() {
        return new Vector3(x,y,0);
    }

    public Vector2 withMaxLength(float max) {
        if(length()<max){
            return this;
        }
        else{
            return withLength(max);
        }
    }

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

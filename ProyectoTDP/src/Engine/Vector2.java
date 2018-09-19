package Engine;

import java.util.Random;

/**
 * a Vector in R2 an his operations
 */
public class Vector2
{
    public static final Vector2 ORIGIN(){return new Vector2(0,0);}
    public static final Vector2 UP (){   return new Vector2(0,1);}
    public static final Vector2 DOWN (){ return new Vector2(0,-1);}
    public static final Vector2 LEFT (){ return new Vector2(-1,0);}
    public static final Vector2 RIGHT (){return new Vector2(1,0);}

    public static final Vector2 UP (float x){   return new Vector2(0,x);}
    public static final Vector2 DOWN (float x){ return new Vector2(0,-x);}
    public static final Vector2 LEFT (float x){ return new Vector2(-x,0);}
    public static final Vector2 RIGHT (float x){return new Vector2(x,0);}

    private static final float Epsilon = 0.01f;

    private float x,y;

    public static Vector2 Random()
    {
        return UP().rotate(new Random(UP().hashCode()).nextFloat());
    }

    public static Vector2 Random(float large)
    {
        return Random().prod(large);
    }



    public float x(){return x;}
    public float y(){return y;}

    public Vector2(float x , float y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2 sum(Vector2 other)
    {
        return new Vector2(x+other.x ,  y+other.y);
    }

    public Vector2 minus(Vector2 other)
    {
        return new Vector2(x-other.x ,  y-other.y);
    }

    public Vector2 prod(float real){
        return new Vector2(x*real,y*real);
    }

    public float length()
    {
        return (float)Math.sqrt(x*x + y*y);
    }

    public Vector2 div(float real)
    {
        if(real == 0)
            throw new EngineException("Never divided by zero");
        return new Vector2(x/real,y/real);
    }

    public Vector2 versor()
    {
        if(x== 0 && y==0)
            return ORIGIN();
        return this.div(length());
    }

    public Vector2 opposite(){
        return prod(-1);
    }


    public Vector2 rotate(float angle)
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
     * but if you want to rotate clockwise put positive values
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
    public Vector2 load(String data)
    {
        Vector2 ret = ORIGIN();
        try{
            String[] a = data.split(",");
            float X = Float.parseFloat(a[0]);
            float Y = Float.parseFloat(a[1]);
            ret = new Vector2(X,Y);
        }
        catch (Exception e){
            System.out.println("Bad Format, value will be origin vector");
        }
        return ret;
    }

    public boolean near(Vector2 other)
    {
        return other.minus(this).length()< Epsilon;
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
        return versor().prod(speed);
    }

    public Vector2 right() {
        return new Vector2(y,-x);
    }
    public Vector2 right(float length) {
        return new Vector2(y,-x).withLength(length);
    }

    public float distanceTo(Vector2 other)
    {
        return minus(other).length();
    }


    /*inner class*/
    private class EngineException extends RuntimeException {
        public EngineException(String msg) {
            super(msg);
        }
    }

    public String toString()
    {
        return String.format("(%f,%f)",x,y);
    }
}

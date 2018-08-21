package Engine;

public class Vector2
{
    public static final Vector2 ORIGIN = new Vector2(0,0);
    public static final Vector2 UP = new Vector2(0,1);
    public static final Vector2 DOWN = new Vector2(0,-1);
    public static final Vector2 LEFT = new Vector2(-1,0);
    public static final Vector2 RIGHT = new Vector2(1,0);

    private float x,y;

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
            throw new EngineException("the zero vector does not have a versor");
        return this.div(length());
    }

    public Vector2 opposite(){
        return prod(-1);
    }


    public String toString(){
        return String.format("(%s,%s)",x,y);
    }


    /*inner class*/
    private class EngineException extends RuntimeException {
        public EngineException(String msg) {
            super(msg);
        }
    }
}

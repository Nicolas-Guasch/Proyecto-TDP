package Engine.Components;

public class Vector2
{
    public static final Vector2 ORIGIN(){return new Vector2(0,0);}
    public static final Vector2 UP (){   return new Vector2(0,1);}
    public static final Vector2 DOWN (){ return new Vector2(0,-1);}
    public static final Vector2 LEFT (){ return new Vector2(-1,0);}
    public static final Vector2 RIGHT (){return new Vector2(1,0);}

    public static float Epsilon = 0.01f;

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
            throw new EngineException("the zero vector does not have a versor");
        return this.div(length());
    }

    public Vector2 opposite(){
        return prod(-1);
    }


    public String toString(){
        return String.format("(%s,%s)",x,y);
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

    /*inner class*/
    private class EngineException extends RuntimeException {
        public EngineException(String msg) {
            super(msg);
        }
    }
}

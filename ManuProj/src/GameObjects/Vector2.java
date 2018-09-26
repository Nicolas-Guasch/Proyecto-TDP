package GameObjects;

public  class Vector2
{


    private final float y;
    private final float x;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2 ORIGIN() {
        return new Vector2(0,0);
    }

    public static Vector2 DOWN() { return new Vector2(0,1); }
    public static Vector2 UP() { return new Vector2(0,-1); }
    public static Vector2 LEFT() {
        return new Vector2(-1,0);
    }
    public static Vector2 RIGHT() {
        return new Vector2(1,0);
    }

    public static Vector2 RIGHT(float len){return new Vector2(len,0);}
    public static Vector2 LEFT(float len){return new Vector2(-len,0);}
    public static Vector2 DOWN(float len){return new Vector2(0,len);}
    public static Vector2 UP(float len){return new Vector2(0,-len);}


    public float getX(){return x;}
    public float getY(){return y;}

    public float y(){return y;}
    public float x(){return x;}

    public Vector2 sum(Vector2 other)
    {
        return new Vector2(x+other.x,y+other.y());
    }

    public Vector2 prod(float constante)
    {
        return new Vector2(x*constante , y*constante);
    }

    public float large()
    {
        return (float)Math.sqrt(x*x+y*y);
    }

    public Vector2 norma()
    {

        return x==0 && y==0 ? new Vector2(0,0): prod(1/large());
    }

    public Vector2 withLarge(float large)
    {
        return norma().prod(large);
    }

    public String toString()
    {
        return "("+x+","+y+")";
    }

    public static Vector2 implicit(Vector2Int other)
    {
        return new Vector2(other.getX(),other.getY());
    }

    public Vector2 Deplie(Vector2 aux, float depl)
    {
        return new Vector2(x*aux.x , (float)(y*aux.y*Math.atan(depl)));
    }
}

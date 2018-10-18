package ADTs;

public class Vector3{
    private final float z;
    private final float x;
    private final float y;

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 Get(int x, int y, int z) {
        return new Vector3(x,y,z);

    }

    public Vector2 xy() {
        return new Vector2(x,y);
    }
    public float z()
    {
        return z;
    }

    public Vector2 v2() {
        return new Vector2(x,y);
    }
}

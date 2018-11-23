package ADTs;

public class Vector3 implements IVector3 {
    private final float z;
    private final float x;
    private final float y;

    public Vector3(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 get(int x, int y, int z) {
        return new Vector3(x,y,z);

    }

    @Override
    public IVector2 xy() {
        return new Vector2(x,y);
    }
    @Override
    public float z()
    {
        return z;
    }


}

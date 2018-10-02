package Levels;

import Engine.Vector2;

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

    public Vector2 xy() {
        return new Vector2(x,y);
    }
    public float z()
    {
        return z;
    }
}

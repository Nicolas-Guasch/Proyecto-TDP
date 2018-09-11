package UtilsBehaviours;

import Engine.Vector2;

public class Bounds {

    private Vector2 topRight, bottomLeft;

    public Bounds(Vector2 topRight, Vector2 bottomLeft)
    {
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
    }

    public Vector2 getBottomLeft() {
        return bottomLeft;
    }

    public Vector2 getTopRight() {
        return topRight;
    }

    public void setTopRight(Vector2 topRight) {
        this.topRight = topRight;
    }

    public Bounds phaseShift(Vector2 phaseShift)
    {
        return new Bounds(topRight.sum(phaseShift),bottomLeft.sum(phaseShift));
    }

    public Bounds prod(float factor)
    {
        return new Bounds(topRight.prod(factor),bottomLeft.prod(factor));
    }

}

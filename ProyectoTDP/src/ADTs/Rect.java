package ADTs;

import Engine.Vector2;

public class Rect {

    private Vector2 topRight, bottomLeft;

    public Rect(Vector2 topRight, Vector2 bottomLeft)
    {
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
    }

    public Vector2 center(){
        return bottomLeft.sum(topRight).prod(0.5f);
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

    public Rect sum(Vector2 phaseShift)
    {
        return new Rect(topRight.sum(phaseShift),bottomLeft.sum(phaseShift));
    }

    public Rect prod(float factor)
    {
        return new Rect(topRight.prod(factor),bottomLeft.prod(factor));
    }

}

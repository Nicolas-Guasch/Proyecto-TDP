package ADTs;

import java.awt.*;

public interface IVector2 {
    float x();

    float y();

    IVector2 sum(IVector2 other);

    IVector2 sub(IVector2 other);

    IVector2 prod(float real);

    float scalarProd(IVector2 other);

    float length();

    float lengthSq();

    IVector2 div(float real);

    IVector2 norma();

    IVector2 rot(float angle);

    float getAngle(IVector2 other);

    IVector2 rotateUnary(float angle);

    float getUnaryAngle(IVector2 other);

    boolean near(IVector2 other);

    boolean equals(IVector2 other);

    IVector2 swapped();

    IVector2 mirrorY();

    IVector2 mirrorX();

    IVector2 withLength(float speed);

    IVector2 right();

    IVector2 right(float length);

    float distanceTo(IVector2 other);

    IVector2 half();

    boolean over(IVector2 v);

    boolean under(IVector2 v);

    boolean onLeft(IVector2 v);

    boolean onRight(IVector2 v);

    Vector3 v3(float z);

    Vector3 v3();

    IVector2 withMaxLength(float max);

    Dimension toDimension();
}

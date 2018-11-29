package Engine.Components;

import ADTs.IVector2;
import ADTs.Vector3;

public interface ITransform {
    IVector2 position();

    void setPosition(IVector2 position);

    void moveTowards(IVector2 direction);

    IVector2 top();

    void setTop(IVector2 top);

    void setFromPrototype(ITransform prototype);

    float getZcomponent();

    void setZcomponent(float zcomponent);

    IVector2 top(float large);

    void rotate(float angle);

    void rotateUnary(float angle);

    void setPosition(Vector3 v);

    void DoMove(IVector2 vector2, int inframes);

    Vector3 position3();

    int getLifetime();
}

package InputManager;

import ADTs.Vector2;

@Deprecated
public class DummyDirectionalInput extends AbstractDirectionalInput{


    private Vector2 vector;

    public void setVector(Vector2 vector)
    {
        this.vector = vector;
    }

    @Override
    public void Destroy() {
        //nothing to do;
    }

    @Override
    public Vector2 Direction() {
        return vector;
    }
}

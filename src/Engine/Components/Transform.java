package Engine.Components;

import Engine.Component;

public class Transform extends Component
{
    private Vector2 position;
    public Transform()
    {
        this(Vector2.ORIGIN());
    }
    private Transform(Vector2 position){this.position = position;}

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void MoveTowards(Vector2 direction){
        position = position.sum(direction);
    }

}


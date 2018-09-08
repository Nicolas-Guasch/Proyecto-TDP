package Engine.Components;

import Engine.Component;
import Engine.Vector2;

public final class Transform extends Component
{
    private Vector2 position;
    private Vector2 top = Vector2.UP();
    // Top of the sprite always look this way


    public Transform()
    {
        this(Vector2.ORIGIN());
    }
    private Transform(Vector2 position)
        {
            this.position = position;
        }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void MoveTowards(Vector2 direction){
        position = position.sum(direction);
    }


    public Vector2 getTop() {
        return top;
    }

    public void setTop(Vector2 top) {
        this.top = top.versor();
    }

    public void resetTop(){
        this.top = Vector2.UP();
    }
}


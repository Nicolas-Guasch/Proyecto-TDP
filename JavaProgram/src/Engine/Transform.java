package Engine;

public class Transform extends Component
{


    private Vector2 position;


    public Transform()
    {
        position = Vector2.ORIGIN;
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

}


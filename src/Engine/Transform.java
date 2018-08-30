package Engine;

public class Transform extends Component<Transform>
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

    @Override
    public void copy(Transform s) {
        position = s.position;
    }

    @Override
    public Transform clone() {
        Transform t = new Transform();
        t.position = position;
        return t;
    }
}


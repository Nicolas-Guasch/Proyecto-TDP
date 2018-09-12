package Scripts;

import Engine.Component;
import Engine.Components.Transform;
import Engine.Vector2;

public class Hunter extends Component
{
    private final float speed;
    private Transform toFollow;

    public Hunter(Transform toFollow, float speed) {
        this.toFollow = toFollow;
        this.speed  =speed;
    }

    @Override
    public void Update() {
        Vector2 dir = toFollow.position().minus(transform().position()).withLength(speed);
        transform().MoveTowards(dir);
    }
}


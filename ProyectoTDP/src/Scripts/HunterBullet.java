package Scripts;

import Engine.Component;
import Engine.Components.Transform;
import ADTs.Vector2;

public class HunterBullet extends Component
{
    private final float speed;
    private Transform toFollow;

    public HunterBullet(Transform toFollow, float speed) {
        this.toFollow = toFollow;
        this.speed  =speed;
    }

    @Override
    public void update() {
        Vector2 dir = toFollow.position().minus(transform().position()).withLength(speed);
        transform().moveTowards(dir);
        transform().setTop(dir);
    }
}


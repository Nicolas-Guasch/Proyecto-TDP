package Entities.Behaviours;

import Engine.Component;
import Engine.GameObject;

public class SimpleBullet extends Component
{

    private final float speed;

    public SimpleBullet(float speed)
    {
        this.speed = speed;
    }

    @Override
    public void Update() {
        transform().MoveTowards(transform().top(speed));
    }
}

package AIs;

import Engine.Component;


/**
 * Implements the behaviour of a Bullet wich moves to his front
 * using the speed indicated in the constructor parameter
 */
public class SimpleBullet extends Component
{
    private final float speed;

    public SimpleBullet(float speed)
    {
        this.speed = speed;
    }

    @Override
    public void update() {
        transform().moveTowards(transform().top(speed));
    }
}

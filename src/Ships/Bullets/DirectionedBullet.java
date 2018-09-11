package Ships.Bullets;

import UtilsBehaviours.Directioned;

public class DirectionedBullet extends BulletBehaviour<DirectionedBullet>
{

    private final float speed;

    public DirectionedBullet(float speed)
    {
        this.speed = speed;
    }

    @Override
    public void Update()
    {
        transform().MoveTowards(transform().getTop(speed));

    }

    @Override
    public DirectionedBullet clone()
    {
        return new DirectionedBullet(speed);
    }
}

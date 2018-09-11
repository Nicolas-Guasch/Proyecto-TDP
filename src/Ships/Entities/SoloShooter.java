package Ships.Entities;


import InputManager.AbstractDiscreteInput;
import ParaTestear.SoundManager;
import Ships.Bullets.AbstractBulletLauncher;
import Ships.Bullets.Bullet;

public class SoloShooter extends AbstractShooter
{

    public SoloShooter(AbstractDiscreteInput shootKey, AbstractBulletLauncher launcher)
    {
        super(shootKey, launcher);
    }

    @Override
    protected void Shoot(Bullet b)
    {
        b.getReferenced().getTransform().setPosition(transform().getPosition().sum(transform().getTop().prod(5)));
        b.getReferenced().SetEnabled(true);
        b.getReferenced().getRenderer().Show();
        SoundManager.Instance().SoloShoot();

    }

}

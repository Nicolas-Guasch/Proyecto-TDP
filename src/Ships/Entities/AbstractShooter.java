package Ships.Entities;

import Broadcaster.*;
import Engine.Component;
import InputManager.AbstractDiscreteInput;
import Ships.Bullets.AbstractBulletLauncher;
import Ships.Bullets.Bullet;

public abstract class AbstractShooter extends Component
{

    private AbstractDiscreteInput shootKey;
    private AbstractBulletLauncher launcher;
    private Broadcaster<Bullet> OnShoot;
    private Invoker<Bullet> invokerOnShoot;

    public AbstractShooter(AbstractDiscreteInput shootKey, AbstractBulletLauncher launcher)
    {
        this.shootKey = shootKey;
        this.launcher = launcher;
        shootKey.OnAction().Suscribe(this::privShoot);
        BroadcasterPackage<Bullet> pack = BroadcasterFactory.GetBroadcaster();
        OnShoot = pack.Broadcaster;
        invokerOnShoot = pack.Invoker;
    }

    private void privShoot(boolean isPressed){
        if(isPressed)
            Shoot(launcher.get());
    }

    protected abstract void Shoot(Bullet b);


    public Broadcaster<Bullet> getOnShoot() {
        return OnShoot;
    }
}

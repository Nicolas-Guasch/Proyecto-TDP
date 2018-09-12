package Entities;

import Broadcaster.*;
import Engine.Component;
import InputManager.AbstractDiscreteInput;



public abstract class AbstractShooter extends Component
{

    private AbstractDiscreteInput shootKey;

    private Broadcaster<Bullet> OnShoot;
    private Invoker<Bullet> invokerOnShoot;

    public AbstractShooter(AbstractDiscreteInput shootKey)
    {
        this.shootKey = shootKey;

        shootKey.OnAction().Suscribe(this::privShoot);
        BroadcasterPackage<Bullet> pack = BroadcasterFactory.GetBroadcaster();
        OnShoot = pack.Broadcaster;
        invokerOnShoot = pack.Invoker;
    }

    private void privShoot(boolean isPressed){
        if(isPressed){
            System.out.println("bla");
        }

    }

    protected abstract void Shoot(Bullet b);


    public Broadcaster<Bullet> getOnShoot() {
        return OnShoot;
    }
}

package Entities.Behaviours;

import Engine.Component;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Reaper;
import Entities.PlayerBullet;
import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteClick;

public class PlayerShooter extends Component
{
    private BulletDirector<PlayerBullet, PlayerBulletBuilder> getter;
    private AbstractDiscreteInput shootInput;

    public PlayerShooter(BulletDirector<PlayerBullet, PlayerBulletBuilder> shooter) {
        this.getter = shooter;
        shootInput = new DiscreteClick(1);
        shootInput.OnAction().Suscribe(this::Shoot);
    }

    private void Shoot(Boolean pressed)
    {
        if(pressed && gameObject()!=null)
        {
            var point = transform().position().sum(transform().top(10));
            getter.create();
            getter.assemble();
            var b = getter.get();
            b.getReferenced().getTransform().setPosition(point);
            Reaper.Instance().add(b);
            Reaper.Instance().killIn(b,400);
        }
    }
}

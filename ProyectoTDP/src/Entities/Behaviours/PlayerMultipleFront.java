package Entities.Behaviours;

import Engine.Component;
import Engine.Vector2;

import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Reaper;
import Entities.PlayerBullet;
import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteClick;

import java.util.Collection;

public class PlayerMultipleFront extends Component
{



    private final Collection<BulletDirector<PlayerBullet, PlayerBulletBuilder>> getters;


    private float phaseShift;
    private AbstractDiscreteInput shootKey;

    public PlayerMultipleFront(float phaseShift, Collection<BulletDirector<PlayerBullet, PlayerBulletBuilder>> getters)
    {
        this.shootKey = new DiscreteClick(1);
        this.phaseShift = phaseShift;
        this.getters = getters;

    }


    @Override
    public void Start() {
        shootKey.OnAction().Suscribe(this::Shoot);
    }

    public void Shoot(boolean isPressed) {



       if(isPressed)
       {
           Vector2 point = transform().position().sum(transform().top().prod(20));
           Vector2 desph = transform().top().right();
           if (getters.size() % 2 == 0) {
               point = point.sum(desph.prod(-phaseShift / 2));
               point = point.sum(desph.prod(-phaseShift * getters.size() / 2));
           }
           else
           {
               point = point.sum(desph.prod(-phaseShift * (getters.size()+1) / 2));
           }

           for (var getter : getters) {
               point = point.sum(desph.prod(phaseShift));
               getter.create();
               getter.assemble();
               var b = getter.get();
               b.getReferenced().getTransform().setPosition(point);
               Reaper.Instance().add(b);
               Reaper.Instance().killIn(b,
                       150);
           }
       }
    }
}
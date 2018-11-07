package Entities.Weapons;


import Engine.Components.Transform;
import ADTs.Vector2;
import AIs.Vibe;
import Entities.Builders.IBullet;
import Entities.Builders.Directors.IBulletDirector;
import Entities.EveryOne;
import Tools.Random;

public class LateralWeapon<DirectorType extends IBulletDirector> extends Weapon
{

    private static final int framesDuration = 150;
    private float phaseShift= 25 ; // 10 muy lejos?

    private final Transform reference;
    private int cantShoots;

    public LateralWeapon(Transform reference, DirectorType generator, int cantShoots) {
        super(generator);
        this.reference = reference;
        this.cantShoots = cantShoots;
    }

    public LateralWeapon(Transform reference, DirectorType generator) {
        this(reference,generator, 1);
    }

    public void setPhaseShift(float phaseShift)
    {
        this.phaseShift = phaseShift;
    }

    public void Shoot()
    {
        Vector2 point = reference.position().sum(reference.top().prod(20));
        Vector2 desph = reference.top().right();
        if (cantShoots % 2 == 0){
            point = point.sum(desph.prod(-phaseShift / 2));
            point = point.sum(desph.prod(-phaseShift * cantShoots / 2));
        }
        else
        {
            point = point.sum(desph.prod(-phaseShift * (cantShoots+1) / 2));
        }
        for (int i=0; i<cantShoots ; i++) {
            point = point.sum(desph.prod(phaseShift));
            generator.create();
            generator.assemble();
            IBullet b = generator.get();
            b.transform().setPosition(point);
            b.entity().addBehaviour(new Vibe(12+Random.value(1,6),3+ Random.value()*2));

            EveryOne.getInstance().add(b.entity());
            EveryOne.getInstance().killIn(b.entity(),framesDuration);
        }
    }

    @Override
    public void Destroy()
    {

    }


}


package Entities.Weapons;

import Engine.Components.Transform;

import ADTs.Vector2;

import Entities.Builders.Directors.IBulletDirector;

import Entities.EveryOne;

public class AngularWeapon<BulletDirectorType extends IBulletDirector> extends Weapon
{

    private float tot_ang = (float) (Math.PI /8);

    private static final int framesDuration = 160;
    private float phaseShift = 25; // 10 muy lejos?
    private String name;
    private final Transform reference;
    private int cantShoots;

    public AngularWeapon(Transform reference, BulletDirectorType generator, int cantShoots) {
        super(generator);
        this.reference = reference;
        this.name = name;
        this.cantShoots = cantShoots;
    }

    public void Shoot()
    {
        Vector2 point = reference.position().sum(reference.top().prod(20));

        float pedazo = (tot_ang/(cantShoots-1));
        float first = (pedazo*((cantShoots-1)/-2f));
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
            var b = generator.get();
            b.transform().setPosition(point);
            b.transform().setTop(reference.top().rot(-first-pedazo*i));
            EveryOne.getInstance().add(b.entity());
            EveryOne.getInstance().killIn(b.entity(),framesDuration);
        }

    }

    @Override
    public void Destroy()
    {
        //TODO: implementar, debe desactivar el shoot
    }



    public void setPhaseShift(float phaseShift) {
        this.phaseShift = phaseShift;
    }
}

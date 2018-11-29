package Entities.Weapons;

import ADTs.IVector2;
import Engine.Components.ITransform;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.IBullet;
import Entities.EveryOne;

//TheWeapon "commonWeaponTie"
public class ShotFront extends Weapon
{

    private final BulletDirector getter;
    private float phaseShift;
    private ITransform reference;

    public ShotFront(float phaseShift, BulletDirector getter, ITransform reference)
    {
        super(getter);
        this.phaseShift = phaseShift;
        this.reference = reference;
        this.getter = getter;

    }

    @Override
    public void Shoot() {

            IVector2 point = reference.position().sum(reference.top().prod(10));
            IVector2 desph = reference.top().swapped();
            point = point.sum(desph.prod(phaseShift));
            phaseShift *= -1;
            getter.create();
            getter.assemble();
            IBullet b = getter.get();
            b.entity().referenced().transform().setPosition(point);
            EveryOne.getInstance().add(b.entity());
            EveryOne.getInstance().killIn(b.entity(),300);
    }

    @Override
    public void Destroy() {

    }
}


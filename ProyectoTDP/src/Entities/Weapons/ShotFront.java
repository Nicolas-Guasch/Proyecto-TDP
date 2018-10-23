package Entities.Weapons;

import Engine.Components.Transform;
import ADTs.Vector2;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.Builders.IBullet;
import Entities.Bullet;
import Entities.EnemyBullet;
import Entities.EveryOne;

//TheWeapon "commonWeaponTie"
public class ShotFront extends Weapon
{

    private final BulletDirector getter;
    private float phaseShift;
    private Transform reference;

    public ShotFront(float phaseShift, BulletDirector getter, Transform reference)
    {
        super(getter);
        this.phaseShift = phaseShift;
        this.reference = reference;
        this.getter = getter;

    }

    @Override
    public void Shoot() {

            Vector2 point = reference.position().sum(reference.top().prod(10));
            Vector2 desph = reference.top().swapped();
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


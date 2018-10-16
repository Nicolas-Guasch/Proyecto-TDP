package Entities.Weapons;

import Engine.Components.Transform;
import ADTs.Vector2;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.TheGrimReaper;

//TheWeapon "commonWeaponTie"
public class EnemyShootFront extends Weapon
{

    private final BulletDirector<EnemyBullet, EnemyBulletBuilder> getter;
    private float phaseShift;
    private Transform reference;

    public EnemyShootFront(float phaseShift, BulletDirector<EnemyBullet, EnemyBulletBuilder> getter, Transform reference)
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
            EnemyBullet b = getter.get();
            b.referenced().transform().setPosition(point);
            TheGrimReaper.Instance().add(b);
            TheGrimReaper.Instance().killIn(b,300);
    }

    @Override
    public void Destroy() {

    }
}


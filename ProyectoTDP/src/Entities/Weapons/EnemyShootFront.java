package Entities.Weapons;

import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.TheGrimReaper;


public class EnemyShootFront extends Weapon
{

    private final BulletDirector<EnemyBullet, EnemyBulletBuilder> getter;
    private float phaseShift;
    private boolean left;
    private Transform reference;

    public EnemyShootFront(float phaseShift, BulletDirector<EnemyBullet, EnemyBulletBuilder> getter, Transform reference)
    {
        super(getter);
        this.phaseShift = phaseShift;
        this.reference = reference;
        this.getter = getter;
        left = true;
    }

    @Override
    public void Shoot() {

            Vector2 point = reference.position().sum(reference.top().prod(10));

            Vector2 desph = reference.top().swapped();

            if(left)
            {
                point = point.sum(desph.prod(phaseShift));
            }
            else
            {
                point = point.minus(desph.prod(phaseShift));
            }
            left = !left;
            getter.create();
            getter.assemble();
            EnemyBullet b = getter.get();
            b.getReferenced().getTransform().setPosition(point);
            TheGrimReaper.Instance().add(b);
            TheGrimReaper.Instance().killIn(b,300);
    }

    @Override
    public void Destroy() {

    }
}


package Entities.Weapons;

import Engine.Components.Transform;
import Entities.Behaviours.MouseFollower;
import Entities.Behaviours.SimpleBullet;
import Entities.Behaviours.ZigZag;
import Entities.Builders.Directors.IBulletDirector;
import Entities.Builders.IBullet;

public class IceWeapon<BulletDirectorType extends IBulletDirector> extends Weapon
{

    private final Transform reference;
    private int magazzine;


    public IceWeapon(Transform reference, BulletDirectorType generator, int magazzine) {
        super(generator);
        this.reference = reference;
        this.magazzine = 3;
    }

    @Override
    public void Shoot()
    {
        magazzine--;
        if(magazzine<0)return;

        generator.create();
        generator.assemble();
        IBullet b = generator.get();
        b.entity().addBehaviour(new ZigZag());
        float angle = 0.2f;
        float extraSpeed = 4f;
        b.entity().addBehaviour(new MouseFollower(angle));
        b.entity().addBehaviour(new SimpleBullet(8f));
        b.entity().referenced().sendMessage(c->c.setActive(true));
        //TODO: sonido
    }

    @Override
    public boolean isEmpty() {
        System.out.println("-");
        return magazzine<=0;
    }

    @Override
    public void Destroy() {

    }
}

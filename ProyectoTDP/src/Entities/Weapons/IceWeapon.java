package Entities.Weapons;

import Engine.Component;
import Engine.Components.ITransform;
import AIs.MouseFollower;
import AIs.SimpleBullet;

import Entities.Builders.Directors.IBulletDirector;
import Entities.Builders.IBullet;

public class IceWeapon<BulletDirectorType extends IBulletDirector> extends Weapon
{

    private final ITransform reference;
    private int magazzine;


    public IceWeapon(ITransform reference, BulletDirectorType generator, int magazzine) {
        super(generator);
        this.reference = reference;
        this.magazzine = 3;
        setName("ice");
    }

    private static void activeTrue(Component c) {
        c.setActive(true);
    }

    @Override
    public void Shoot()
    {
        magazzine--;
        if(magazzine<0)return;

        generator.create();
        generator.assemble();
        IBullet b = generator.get();

        float angle = 0.2f;
        //float extraSpeed = 4f;
        b.entity().addBehaviour(new MouseFollower(angle));
        b.entity().addBehaviour(new SimpleBullet(8f));
        b.entity().referenced().sendMessage(IceWeapon::activeTrue);
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

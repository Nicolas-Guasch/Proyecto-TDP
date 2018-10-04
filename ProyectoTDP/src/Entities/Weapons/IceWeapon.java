package Entities.Weapons;

import Engine.Components.Transform;
import Entities.Behaviours.MouseFollower;
import Entities.Behaviours.SimpleBullet;
import Entities.Behaviours.ZigZag;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.Directors.IBulletDirector;
import Entities.Builders.IBullet;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Bullets.ColdFireMaker;
import Entities.PlayerBullet;
import Entities.Ships.PlayerShipDirector;
import Scripts.AlwaysRotate;

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
        b.entity().addBehaviour(new MouseFollower());
        b.entity().addBehaviour(new SimpleBullet(0.01f));
        b.entity().getReferenced().sendMessage(c->c.setActive(true));
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
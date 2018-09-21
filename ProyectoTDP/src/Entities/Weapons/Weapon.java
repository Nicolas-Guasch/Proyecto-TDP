package Entities.Weapons;

import Entities.Builders.Directors.IBulletDirector;

public abstract class Weapon<BulletDirectorType extends IBulletDirector>
{
    protected BulletDirectorType generator;

    public Weapon(BulletDirectorType generator)
    {
        this.generator  = generator;
    }

    public abstract void Shoot();
    public abstract void Destroy();



}

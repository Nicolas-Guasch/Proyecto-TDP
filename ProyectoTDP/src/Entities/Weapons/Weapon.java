package Entities.Weapons;

import Entities.Builders.Directors.IBulletDirector;

public abstract class Weapon<BulletDirectorType extends IBulletDirector>
{
    protected BulletDirectorType generator;
    private String name;
    public Weapon(BulletDirectorType generator)
    {
        this.generator  = generator;
    }

    public abstract void Shoot();
    public abstract void Destroy();
    public boolean isEmpty()
    {
        return false;
    }

    public final void setName(String name){
        this.name=name;
    }
    public final String spriteName(){
        return name;
    }


}

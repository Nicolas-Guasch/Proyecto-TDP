package Entities;

import Engine.GameObject;
import Entities.Weapons.Weapon;
import Entities.Weapons.WeaponSet;

public abstract class Ship
        /*<MyType extends Ship<MyType,OponentType>, OponentType extends Ship<OponentType, MyType>> */
        extends Entity implements IShip
{
    protected WeaponSet weapons;

    protected Ship(GameObject referenced, WeaponSet weapons) {
        super(referenced);
        this.weapons = weapons;
    }

    public void onDeath(){
        weapons.setActive(false);
    }

    public void addWeapon(Weapon p)
    {
        weapons.add(p);
    }
    public void removeWeapon(Weapon p)
    {
        weapons.remove(p);
    }


}

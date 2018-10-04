package Entities.Ships;

import Engine.GameObject;
import Entities.Entity;
import Entities.Weapons.Weapon;
import Entities.Weapons.WeaponSet;
import IAs.Pilot;

public abstract class Ship<Son extends Ship<Son>>
        /*<MyType extends Ship<MyType,OponentType>, OponentType extends Ship<OponentType, MyType>> */
        extends Entity<Son> implements IShip
{
    protected WeaponSet weapons;
    private Pilot pilot;

    Ship(GameObject referenced, WeaponSet weapons) {
        super(referenced);
        this.weapons = weapons;
    }



    public void onDeath()
    {
        super.onDeath();

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


    public void setPilot(Pilot pilot)
    {
        this.pilot = pilot;
        getReferenced().removeComponent(pilot);
        getReferenced().addComponent(pilot);
    }

    public Pilot getPilot() {
        return pilot;
    }
}

package Entities.Ships;

import Engine.GameObject;
import Entities.Entity;
import Entities.Weapons.Weapon;
import Entities.Weapons.WeaponSet;
import EntitiesVisitor.VisitorEntitie;
import IAs.Pilot;

public abstract class Ship extends Entity implements IShip
{
    protected WeaponSet weapons;
    private Pilot pilot;

    protected Ship(GameObject referenced, WeaponSet weapons, VisitorEntitie visitor) {
        super(referenced,visitor);
        this.weapons = weapons;
    }

    public void onDeath(){
        super.onDeath();
        weapons.setActive(false);
        if (pilot != null) {
            pilot.setActive(false);
        }
    }

    public void addWeapon(Weapon p){
        weapons.add(p);
    }
    public void removeWeapon(Weapon p){
        weapons.remove(p);
    }
    public void setPilot(Pilot pilot){
        this.pilot = pilot;
        referenced().removeComponent(pilot);
        referenced().addComponent(pilot);
    }
    public Pilot getPilot() {
        return pilot;
    }
}

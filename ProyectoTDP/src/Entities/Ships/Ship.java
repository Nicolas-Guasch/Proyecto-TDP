package Entities.Ships;

import ADTs.IVector2;
import Engine.IGameObject;
import Tools.AnimatorsVolatiles;
import Entities.Entity;
import Entities.Weapons.Weapon;
import Entities.Weapons.Arsenal;
import IAs.Pilot;

public abstract class Ship extends Entity implements IShip
{
    protected Arsenal weapons;
    private Pilot pilot;

    protected Ship(IGameObject referenced, Arsenal weapons) {
        super(referenced);
        this.weapons = weapons;
    }

    public void onDeath(){
        super.onDeath();
        IVector2 v = referenced().transform().position();
        AnimatorsVolatiles.getInstance().getExplosion(v);
        weapons.setActive(false);
        if (pilot != null) {
            pilot.setActive(false);
        }
    }

    public void addWeapon(Weapon p){
        weapons.add(p);
    }

    public void setPilot(Pilot pilot){
        this.pilot = pilot;
        referenced().removeComponent(pilot);
        referenced().addComponent(pilot);
    }
    public Pilot getPilot() {
        return pilot;
    }

    public Arsenal getArsenal() {
        return weapons;
    }
}

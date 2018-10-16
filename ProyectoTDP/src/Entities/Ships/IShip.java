package Entities.Ships;

import Entities.Weapons.Weapon;
import IAs.Pilot;

public interface IShip
{
    void addWeapon(Weapon p);
    void removeWeapon(Weapon p);
    Pilot getPilot();
    void setPilot(Pilot pilot);

}

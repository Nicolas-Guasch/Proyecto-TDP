package Entities;

import Entities.Weapons.Weapon;

public interface IShip
{
    void addWeapon(Weapon p);
    void removeWeapon(Weapon p);
}

package Mementos;

import Entities.Entity;
import Entities.EntityData;
import Entities.Weapons.WeaponSet;
import IAs.Pilot;

public interface IMementoPlayer
{
    void setData(EntityData data);
    void setWeaponSet(WeaponSet weapons);
    void setPilot(Pilot pilot);
    WeaponSet getWeapons();
    Pilot getPilot();
    EntityData getData();
}

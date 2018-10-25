package Mementos;

import Entities.EntityData;
import Entities.Weapons.Arsenal;
import IAs.Pilot;

public interface IMementoPlayer
{
    void setData(EntityData data);
    void setWeaponSet(Arsenal weapons);
    void setPilot(Pilot pilot);
    Arsenal getWeapons();
    Pilot getPilot();
    EntityData getData();
}

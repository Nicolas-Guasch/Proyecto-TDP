package Mementos;

import Entities.EntityData;
import Entities.Weapons.WeaponSet;
import GameData.GameSettings;
import IAs.Pilot;

public class MementoPlayer implements IMementoPlayer{


    private WeaponSet weapons;
    private Pilot pilot;
    private EntityData data;


    public MementoPlayer(WeaponSet weapons, Pilot pilot) {
        setWeaponSet(weapons);
        setPilot(pilot);
        setData(null);
    }

    @Override
    public void setData(EntityData data) {
        data = GameSettings.GetInstance().PlayerInitialData.clone();
    }

    @Override
    public void setWeaponSet(WeaponSet weapons) {
        this.weapons = weapons.clone();
        this.weapons.setActive(false);
    }

    @Override
    public void setPilot(Pilot pilot) {
        this.pilot = pilot.clone();
        this.pilot.setActive(false);
    }

    public WeaponSet getWeapons() {
        return weapons;
    }

    public Pilot getPilot() {
        return pilot;
    }

    @Override
    public EntityData getData() {
        return data;
    }
}

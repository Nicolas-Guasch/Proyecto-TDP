package Mementos;

import Entities.EntityData;
import Entities.Weapons.Arsenal;
import GameData.GameSettings;
import IAs.Pilot;

public class MementoPlayer implements IMementoPlayer{


    private Arsenal weapons;
    private Pilot pilot;
    private EntityData data;


    public MementoPlayer(Arsenal weapons, Pilot pilot) {
        setWeaponSet(weapons);
        setPilot(pilot);
        setData(null);
    }

    @Override
    public void setData(EntityData data) {
        data = GameSettings.GetInstance().PlayerInitialData.clone();
    }

    @Override
    public void setWeaponSet(Arsenal weapons) {
        this.weapons = weapons.clone();
        this.weapons.setActive(false);
    }

    @Override
    public void setPilot(Pilot pilot) {
        this.pilot = pilot.clone();
        this.pilot.setActive(false);
    }

    public Arsenal getWeapons() {
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

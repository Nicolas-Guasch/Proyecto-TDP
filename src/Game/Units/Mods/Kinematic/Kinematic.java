package Game.Units.Mods.Kinematic;

import Game.Units.Mod;
import Game.Units.UnitData;

public abstract class Kinematic extends Mod {

    public void setSpeed(UnitData data,int x){
        data.setSpeed(x);
    }

    public int getSpeed(UnitData data){
        return data.getSpeed();
    }

    public abstract void move(UnitData data);
}

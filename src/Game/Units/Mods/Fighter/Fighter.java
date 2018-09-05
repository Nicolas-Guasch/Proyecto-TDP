package Game.Units.Mods.Fighter;

import Game.Units.Mod;
import Game.Units.UnitData;

public abstract class Fighter extends Mod {

    public void setImpact(UnitData data,int x){
        data.setImpact(x);
    }

    public int getImpact(UnitData data){
        return data.getImpact();
    }

    public void loseWeapon(UnitData data){
        data.loseWeapon();
    }

    public void loadWeapon(UnitData data,Weapon w){
        data.loadWeapon(w);
    }

    public abstract void attack(UnitData data);

}

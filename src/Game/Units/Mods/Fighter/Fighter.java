package Game.Units.Mods.Fighter;

import Game.Units.Mod;

public abstract class Fighter extends Mod {

    protected int impact;
    protected Weapon weapon;

    public Fighter(int i, Weapon w){
        impact=i;
        weapon=w;
    }

    public void setImpact(int x){
        impact=x;
    }

    public int getImpact(){
        return impact;
    }

    public void loseWeapon(){
        weapon=null;
    }

    public void loadWeapon(Weapon w){
        weapon=w;
    }

    public abstract void attack();

}

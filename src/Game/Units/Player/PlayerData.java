package Game.Units.Player;

import Game.Units.Mods.Fighter.Weapon;
import Game.Units.UnitData;

public class PlayerData implements UnitData {

    protected int life;
    protected int speed;
    protected int impact;
    protected Weapon weapon;
    protected int maxLife;

    public PlayerData(int l,int s,int i,Weapon w){
        life=l;
        maxLife=l;
        speed=s;
        impact=i;
        weapon=w;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int l) {
        life=l;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
    }

    @Override
    public void setImpact(int x) {
        impact=x;
    }

    @Override
    public int getImpact() {
        return impact;
    }

    @Override
    public void loseWeapon() {

    }

    @Override
    public void loadWeapon(Weapon w) {
        weapon=w;
    }

    @Override
    public void setSpeed(int x) {
        speed=x;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void addLife(int x) {
        life+=x;
    }
}

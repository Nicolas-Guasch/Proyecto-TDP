package Game.Units.Rewards;

import Game.Units.Mods.Fighter.Weapon;
import Game.Units.UnitData;

public class RewardData implements UnitData {

    protected int life;
    protected int maxLife;
    protected int speed;

    public RewardData(int l, int s) {
        life=l;
        maxLife=l;
        speed=s;
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
        return maxLife ;
    }

    @Override
    public void setImpact(int x) {

    }

    @Override
    public int getImpact() {
        return 0;
    }

    @Override
    public void loseWeapon() {

    }

    @Override
    public void loadWeapon(Weapon w) {

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

    }
}

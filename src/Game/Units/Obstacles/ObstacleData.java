package Game.Units.Obstacles;

import Game.Units.Mods.Fighter.Weapon;
import Game.Units.UnitData;

public class ObstacleData implements UnitData {

    protected int life;
    protected int maxLife;

    public ObstacleData(int l){
        life=l;
        maxLife=l;
    }

    public int getLife(){
        return life;
    }

    public void setLife(int l){
        life=l;
    }

    @Override
    public int getMaxLife() {
        return maxLife;
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

    }

    @Override
    public int getSpeed() {
        return 0;
    }

    @Override
    public void addLife(int x) {

    }


}

package Game.Units;

import Game.Units.Mods.Fighter.Weapon;

public interface UnitData {

    int getLife();
    void setLife(int l);
    int getMaxLife();
    void addLife(int x);

    void setImpact(int x);
    int getImpact();
    void loseWeapon();
    void loadWeapon(Weapon w);

    void setSpeed(int x);
    int getSpeed();


}

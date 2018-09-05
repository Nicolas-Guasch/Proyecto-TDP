package Game.Units.Mods.Life;

import Game.Units.UnitData;

public class LifeLooseWeapon extends Life {
    @Override
    protected void inCritic(UnitData data) {
        data.loseWeapon();
    }
}

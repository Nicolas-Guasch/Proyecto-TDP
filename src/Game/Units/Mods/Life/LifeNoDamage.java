package Game.Units.Mods.Life;

import Game.Units.UnitData;

public class LifeNoDamage extends Life {

    @Override
    public void takeDamage(UnitData data,int punch) {

    }

    @Override
    protected void inCritic(UnitData data) {

    }

    @Override
    public void setLife(UnitData data,int x) {
        //Bloquea el set
    }
}

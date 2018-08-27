package Game.Units.Rewards;

import Game.Units.BasicUnit;
import Game.Units.Mods.Fighter.FighterNone;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;

public abstract class Reward extends BasicUnit {

    public Reward(Life l, Kinematic k) {
        super(l, k, new FighterNone());
    }
}

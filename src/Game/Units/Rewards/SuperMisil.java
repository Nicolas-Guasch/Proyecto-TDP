package Game.Units.Rewards;

import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;
import Game.Units.OnCollide;

public class SuperMisil extends Reward{

    public SuperMisil(Life l, Kinematic k) {
        super(l, k);
    }

    @Override
    public void accept(OnCollide c) {

    }
}

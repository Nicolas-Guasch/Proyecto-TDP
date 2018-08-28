package Game.Units.Rewards;

import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;
import Game.Units.OnCollide;

public class CongelaTiempo extends Reward {
    public CongelaTiempo(Life l, Kinematic k) {
        super(l, k);
    }

    @Override
    public void accept(OnCollide c) {

    }
}

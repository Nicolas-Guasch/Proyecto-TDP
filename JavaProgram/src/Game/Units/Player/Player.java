package Game.Units.Player;

import Game.Units.BasicUnit;
import Game.Units.Mods.Fighter.Fighter;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;

public abstract class Player extends BasicUnit {
    public Player(Life f, Kinematic k, Fighter s) {
        super(f, k, s);
    }
}

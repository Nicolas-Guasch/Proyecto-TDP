package Game.Units.Enemies;

import Game.Units.BasicUnit;
import Game.Units.Mods.Fighter.Fighter;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;

public abstract class Enemy extends BasicUnit {
    public Enemy(Life l, Kinematic k, Fighter f) {
        super(l, k, f);
    }
}

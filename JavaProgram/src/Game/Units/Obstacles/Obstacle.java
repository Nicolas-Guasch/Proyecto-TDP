package Game.Units.Obstacles;

import Game.Units.BasicUnit;
import Game.Units.Mods.Fighter.Fighter;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Kinematic.KinematicNone;
import Game.Units.Mods.Life.Life;

public abstract class Obstacle extends BasicUnit {
    public Obstacle(Life l, Fighter f) {
        super(l, new KinematicNone(),f);
    }

    @Override
    public void changeMod(Kinematic k) {

    }
}

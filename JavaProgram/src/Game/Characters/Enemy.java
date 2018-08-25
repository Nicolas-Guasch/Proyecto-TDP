package Game.Characters;

import Game.Characters.Kinematic.Kinematic;
import Game.Characters.Life.Life;

public abstract class Enemy extends BasicUnit{
    public Enemy(Life f, Kinematic k, Shooter s) {
        super(f, k, s);
    }
}

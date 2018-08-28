package Game.Units;

import Game.Units.Effects.Effect;
import Game.Units.Enemies.Enemy;
import Game.Units.Enemies.ShotEnemy;
import Game.Units.Player.ShotPlayer;
import Game.Units.Obstacles.Obstacle;
import Game.Units.Player.Player;

public interface OnCollide {

    //Aqui se resuelven cuando dos Obstacles colisionan
    abstract void collideEnemy(Enemy e);

    abstract void collidePlayer(Player p);

    abstract void collideShot(ShotPlayer s);

    abstract void collideShot(ShotEnemy s);

    abstract void collideObstacleEnemy(Obstacle o);

    abstract void collideObstacleAll(Obstacle o);

    abstract void collideEfect(Effect e);
}

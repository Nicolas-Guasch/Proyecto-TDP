package Game.Mapa;
import Game.Units.Enemies.Enemy;

public abstract class EnemyFactory {

    protected int life,speed,range;
    public abstract Enemy createEnemy();
}

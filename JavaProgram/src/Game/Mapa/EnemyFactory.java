package Game.Mapa;
import Game.Characters.Enemy;

public abstract class EnemyFactory {

    protected int life,speed,range;
    public abstract Enemy createEnemy();
}

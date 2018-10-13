package TesterLevels;

import Entities.Ships.EnemyShip;

//TODO: borrar Wave
public abstract class EnemiesStructure implements Iterable<EnemyShip>
{

    public abstract void addEnemy(EnemyShip enemy);
    public abstract int remaining();

}

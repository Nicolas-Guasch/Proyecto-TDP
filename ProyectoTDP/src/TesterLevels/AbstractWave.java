package TesterLevels;

import Entities.Builders.AbstractRewardFactory;
import Entities.Ships.EnemyShipDirector;
import Entities.Ships.EnemyShip;

public abstract class AbstractWave
{

    public abstract void run();
    public abstract Iterable<EnemyShip> addEnemies(EnemyShipDirector director, int cant);
    public abstract void addReward(AbstractRewardFactory reward);
    public abstract boolean EmptyWave();
}

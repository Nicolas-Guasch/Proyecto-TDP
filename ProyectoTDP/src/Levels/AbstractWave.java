package Levels;

import Engine.Component;
import Entities.Builders.AbstractRewardFactory;
import Entities.Builders.Directors.EnemyShipDirector;
import Entities.EnemyShip;

public abstract class AbstractWave
{

    public abstract Component run();
    public abstract Iterable<EnemyShip> addEnemies(EnemyShipDirector director, int cant);
    public abstract void addReward(AbstractRewardFactory reward);
    public abstract boolean EmptyWave();
}

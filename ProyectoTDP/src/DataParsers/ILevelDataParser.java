package DataParsers;

import ADTs.Vector2;
import Entities.Ships.EnemyShipBuilder;
import Rewards.Reward;
import Rewards.RewardKey;

import java.util.Collection;

public interface ILevelDataParser
{
    /**
     * @param key key to find file data
     */
    void setKey(String key);
    Collection<Vector2> enemiesPositions();
    Collection<Vector2> obstaclesPositions();
    Collection<EnemyShipBuilder> enemies();
    Collection<RewardKey> rewards();

}

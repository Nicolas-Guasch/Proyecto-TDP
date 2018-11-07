package DataParsers;

import ADTs.Vector2;
import Entities.Ships.EnemyShipBuilder;
import Rewards.RewardKey;

import java.util.Collection;
import java.util.List;

public interface ILevelsData
{
    /**
     * @param key key to find file data
     */

    void setKey(String key);

    List<Vector2> enemiesPositions();
    List<Vector2> obstaclesPositions();
    List<EnemyShipBuilder> enemies();
    List<RewardKey> rewards();

}

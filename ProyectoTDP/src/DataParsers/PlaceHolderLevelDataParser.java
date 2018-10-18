package DataParsers;

import ADTs.Vector2;
import Entities.Ships.EnemiesBuilders.FastTieMaker;
import Entities.Ships.EnemiesBuilders.WhiteTieMaker;
import Entities.Ships.EnemyShipBuilder;
import Rewards.RewardFactory;
import Rewards.RewardKey;

import java.util.*;

public class PlaceHolderLevelDataParser implements ILevelDataParser
{

    private Map<String,List<Vector2>> enemsPos, obstpos;
    private Map<String,List<EnemyShipBuilder>> enemies;
    private Map<String,List<RewardKey>> rew;

    private String L1 = "level1";
    private String L2 = "level2";
    private String L3 = "level3";

    private String currentKey = L1;

    PlaceHolderLevelDataParser()
    {
        enemies = new TreeMap<>();
        obstpos = new TreeMap<>();
        enemsPos = new TreeMap<>();
        rew = new TreeMap<>();

        rew.put(L1,new LinkedList<>());
        rew.put(L2,new LinkedList<>());
        rew.put(L3,new LinkedList<>());

        enemies.put(L1,new LinkedList<>());
        enemies.put(L2,new LinkedList<>());
        enemies.put(L3,new LinkedList<>());



        obstpos.put(L1,new LinkedList<>());
        obstpos.put(L2,new LinkedList<>());
        obstpos.put(L3,new LinkedList<>());

        enemsPos.put(L1,new LinkedList<>());
        enemsPos.put(L2,new LinkedList<>());
        enemsPos.put(L3,new LinkedList<>());

        initenempos();
        initObsts();
        initrew();
        initenembuilds();

    }

    private void initenembuilds()
    {
        enemies.get(L1).add(new WhiteTieMaker());

        enemies.get(L2).add(new WhiteTieMaker());
        enemies.get(L2).add(new FastTieMaker());

        enemies.get(L3).add(new FastTieMaker());
    }

    private void initrew()
    {
        rew.get(L1).add(RewardFactory.SHIELDREWARD);
        rew.get(L1).add(RewardFactory.WEAPON5REWARD);

        rew.get(L2).add(RewardFactory.SHIELDREWARD);
        rew.get(L2).add(RewardFactory.SHIELDREWARD);
        rew.get(L2).add(RewardFactory.WEAPONICEREWARD);

        rew.get(L3).add(RewardFactory.SHIELDREWARD);
        rew.get(L3).add(RewardFactory.WEAPONICEREWARD);
    }

    private void initObsts() {
        enemsPos.get(L1).add(new Vector2(-400,0));
        enemsPos.get(L1).add(new Vector2(0,0));
        enemsPos.get(L1).add(new Vector2(400,0));
    }

    private void initenempos()
    {
        enemsPos.get(L1).add(new Vector2(-400/1.5f,350));
        enemsPos.get(L1).add(new Vector2(-300/1.5f,350));
        enemsPos.get(L1).add(new Vector2(-200/1.5f,350));
        enemsPos.get(L1).add(new Vector2(-100/1.5f,350));
        enemsPos.get(L1).add(new Vector2(000/1.5f,350));
        enemsPos.get(L1).add(new Vector2(100/1.5f,350));
        enemsPos.get(L1).add(new Vector2(200/1.5f,350));
        enemsPos.get(L1).add(new Vector2(300/1.5f,350));
        enemsPos.get(L1).add(new Vector2(400/1.5f,350));

        enemsPos.get(L2).add(new Vector2(-400,550));
        enemsPos.get(L2).add(new Vector2(-300,550));
        enemsPos.get(L2).add(new Vector2(-200,550));
        enemsPos.get(L2).add(new Vector2(-100,550));
        enemsPos.get(L2).add(new Vector2(000,550));
        enemsPos.get(L2).add(new Vector2(100,550));
        enemsPos.get(L2).add(new Vector2(200,550));
        enemsPos.get(L2).add(new Vector2(300,550));
        enemsPos.get(L2).add(new Vector2(400,550));

        enemsPos.get(L2).add(new Vector2(-400,550));
        enemsPos.get(L2).add(new Vector2(-300,550));
        enemsPos.get(L2).add(new Vector2(-200,550));
        enemsPos.get(L2).add(new Vector2(-100,550));
        enemsPos.get(L2).add(new Vector2(000,550));
        enemsPos.get(L2).add(new Vector2(100,550));
        enemsPos.get(L2).add(new Vector2(200,550));
        enemsPos.get(L2).add(new Vector2(300,550));
        enemsPos.get(L2).add(new Vector2(400,550));

        enemsPos.get(L3).add(new Vector2(-400,550));
        enemsPos.get(L3).add(new Vector2(-300,550));
        enemsPos.get(L3).add(new Vector2(-200,550));
        enemsPos.get(L3).add(new Vector2(-100,550));
        enemsPos.get(L3).add(new Vector2(000,550));
        enemsPos.get(L3).add(new Vector2(100,550));
        enemsPos.get(L3).add(new Vector2(200,550));
        enemsPos.get(L3).add(new Vector2(300,550));
        enemsPos.get(L3).add(new Vector2(400,550));

        enemsPos.get(L3).add(new Vector2(-400,500));
        enemsPos.get(L3).add(new Vector2(-300,500));
        enemsPos.get(L3).add(new Vector2(-200,500));
        enemsPos.get(L3).add(new Vector2(-100,500));
        enemsPos.get(L3).add(new Vector2(000,500));
        enemsPos.get(L3).add(new Vector2(100,500));
        enemsPos.get(L3).add(new Vector2(200,500));
        enemsPos.get(L3).add(new Vector2(300,500));
        enemsPos.get(L3).add(new Vector2(400,500));

    }

    @Override
    public void setKey(String key) {
        currentKey = L1;
    }

    @Override
    public List<Vector2> enemiesPositions() {
        return enemsPos.get(currentKey);
    }

    @Override
    public List<Vector2> obstaclesPositions() {
        return obstpos.get(currentKey);
    }

    @Override
    public List<EnemyShipBuilder> enemies() {
        return enemies.get(currentKey);
    }

    @Override
    public List<RewardKey> rewards() {
        return rew.get(currentKey);
    }
}

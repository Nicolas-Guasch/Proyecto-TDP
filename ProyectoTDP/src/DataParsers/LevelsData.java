package DataParsers;

import ADTs.Vector2;
import Entities.Ships.EnemiesBuilders.*;
import Entities.Ships.EnemyShipBuilder;
import Rewards.RewardsManager;
import Rewards.RewardKey;

import java.util.*;

public class LevelsData implements ILevelsData
{

    private Map<String,List<Vector2>> enemsPos, obstpos;
    private Map<String,List<EnemyShipBuilder>> enemies;
    private Map<String,List<RewardKey>> rew;

    private String L1 = "level1";
    private String L2 = "level2";
    private String L3 = "level3";

    private String currentKey = L1;

    LevelsData()
    {
        enemies = new TreeMap<String, List<EnemyShipBuilder>>();
        obstpos = new TreeMap<String, List<Vector2>>();
        enemsPos = new TreeMap<String, List<Vector2>>();
        rew = new TreeMap<String, List<RewardKey>>();

        rew.put(L1,new LinkedList<RewardKey>());
        rew.put(L2,new LinkedList<RewardKey>());
        rew.put(L3,new LinkedList<RewardKey>());

        enemies.put(L1,new LinkedList<EnemyShipBuilder>());
        enemies.put(L2,new LinkedList<EnemyShipBuilder>());
        enemies.put(L3,new LinkedList<EnemyShipBuilder>());



        obstpos.put(L1,new LinkedList<Vector2>());
        obstpos.put(L2,new LinkedList<Vector2>());
        obstpos.put(L3,new LinkedList<Vector2>());

        enemsPos.put(L1,new LinkedList<Vector2>());
        enemsPos.put(L2,new LinkedList<Vector2>());
        enemsPos.put(L3,new LinkedList<Vector2>());

        initenempos();
        initObsts();
        initrew();
        initenembuilds();

    }

    private void initenembuilds()
    {

        enemies.get(L1).add(new WhiteTieMaker());
        enemies.get(L1).add(new TieCrazyMaker());
        //enemies.get(L1).add(new DizzyMaker());

        enemies.get(L2).add(new DizzyMaker());
        enemies.get(L2).add(new FastTieMaker());
        enemies.get(L2).add(new TieCrazyMaker());
        enemies.get(L2).add(new WhiteTieMaker());
        enemies.get(L2).add(new FastTieMaker());

        enemies.get(L3).add(new TieCrazyMaker());
        //nemies.get(L3).add(new DizzyMaker());
        //enemies.get(L3).add(new FastTieMaker());
        enemies.get(L3).add(new FullKamikazeeMaker());
        enemies.get(L3).add(new FastTieMaker());
    }

    private void initrew()
    {



        rew.get(L1).add(RewardsManager.FORCE);
        rew.get(L1).add(RewardsManager.SHIELD);
        rew.get(L1).add(RewardsManager.ICE);
        rew.get(L1).add(RewardsManager.HEALTH);
        rew.get(L1).add(RewardsManager.HEALTH);
        rew.get(L1).add(RewardsManager.HEALTH);
        rew.get(L1).add(RewardsManager.FIRESHIELD);
        rew.get(L1).add(RewardsManager.FIRESHIELD);

        rew.get(L2).add(RewardsManager.FIRESHIELD);
        rew.get(L2).add(RewardsManager.FIRESHIELD);
        rew.get(L2).add(RewardsManager.FIRESHIELD);
        rew.get(L2).add(RewardsManager.HANSUPPORT);
        rew.get(L2).add(RewardsManager.HEALTH);
        rew.get(L2).add(RewardsManager.HEALTH);
        rew.get(L2).add(RewardsManager.HEALTH);
        rew.get(L2).add(RewardsManager.ICE);
        rew.get(L2).add(RewardsManager.FORCE);


        rew.get(L3).add(RewardsManager.ICE);
        rew.get(L3).add(RewardsManager.FIRESHIELD);
        rew.get(L3).add(RewardsManager.FIRESHIELD);
        rew.get(L3).add(RewardsManager.FIVEWEAPON);
        rew.get(L3).add(RewardsManager.HANSUPPORT);
        rew.get(L3).add(RewardsManager.HEALTH);
        rew.get(L3).add(RewardsManager.HEALTH);


    }

    private void initObsts() {
        obstpos.get(L1).add(new Vector2(-200,-100));
        obstpos.get(L1).add(new Vector2(200,-100));

        obstpos.get(L2).add(new Vector2(-300,00));
        obstpos.get(L2).add(new Vector2(300,00));

        obstpos.get(L3).add(new Vector2(-200,100));
        obstpos.get(L3).add(new Vector2(200,100));
        obstpos.get(L3).add(new Vector2(0,-100));

    }

    private void initenempos()
    {
        enemsPos.get(L1).add(new Vector2(-750,180));
        enemsPos.get(L1).add(new Vector2(-450,300));
        enemsPos.get(L1).add(new Vector2(-150,180));

        enemsPos.get(L1).add(new Vector2(750,300));
        enemsPos.get(L1).add(new Vector2(450,180));
        enemsPos.get(L1).add(new Vector2(150,300));



        enemsPos.get(L2).add(new Vector2(-750,180));
        enemsPos.get(L2).add(new Vector2(-450,300));
        enemsPos.get(L2).add(new Vector2(-450,300));

        enemsPos.get(L2).add(new Vector2(750,300));
        enemsPos.get(L2).add(new Vector2(450,180));
        enemsPos.get(L2).add(new Vector2(250,300));
        enemsPos.get(L2).add(new Vector2(250,300));
        enemsPos.get(L2).add(new Vector2(250,300));
        enemsPos.get(L2).add(new Vector2(250,300));


        enemsPos.get(L3).add(new Vector2(-750,180));
        enemsPos.get(L3).add(new Vector2(-450,300));
        enemsPos.get(L3).add(new Vector2(-150,180));

        enemsPos.get(L3).add(new Vector2(150,400));
        enemsPos.get(L3).add(new Vector2(150,400));
        enemsPos.get(L3).add(new Vector2(150,300));


    }

    @Override
    public void setKey(String key) {
        currentKey = key;
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

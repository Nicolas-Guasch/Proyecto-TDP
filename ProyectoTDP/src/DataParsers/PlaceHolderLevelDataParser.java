package DataParsers;

import ADTs.Vector2;
import Entities.Ships.EnemiesBuilders.*;
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
        enemies.get(L1).add(new WachinMaker());

        enemies.get(L2).add(new DizzyMaker());
        enemies.get(L2).add(new FastTieMaker());
        enemies.get(L2).add(new WachinMaker());
        enemies.get(L2).add(new WhiteTieMaker());
        enemies.get(L2).add(new FastTieMaker());

        enemies.get(L3).add(new WachinMaker());
        enemies.get(L3).add(new DizzyMaker());
        enemies.get(L3).add(new FastTieMaker());
        enemies.get(L3).add(new FullKamikazeeMaker());
        enemies.get(L3).add(new FastTieMaker());
    }

    private void initrew()
    {



        rew.get(L1).add(RewardFactory.FORCE);
        rew.get(L1).add(RewardFactory.SHIELD);
        rew.get(L1).add(RewardFactory.ICE);
        rew.get(L1).add(RewardFactory.HEALTH);

        rew.get(L2).add(RewardFactory.FIRESHIELD);
        rew.get(L2).add(RewardFactory.HANSUPPORT);
        rew.get(L2).add(RewardFactory.HEALTH);
        rew.get(L2).add(RewardFactory.ICE);
        rew.get(L2).add(RewardFactory.SHIELD);
        rew.get(L2).add(RewardFactory.FORCE);

        rew.get(L3).add(RewardFactory.FIVEWEAPON);
        rew.get(L3).add(RewardFactory.HANSUPPORT);
        rew.get(L3).add(RewardFactory.HEALTH);
        rew.get(L3).add(RewardFactory.HEALTH);


    }

    private void initObsts() {
        obstpos.get(L1).add(new Vector2(-300,-100));
        obstpos.get(L1).add(new Vector2(300,-100));

        obstpos.get(L2).add(new Vector2(-300,-100));
        obstpos.get(L2).add(new Vector2(300,-100));

        obstpos.get(L3).add(new Vector2(-300,-100));
        obstpos.get(L3).add(new Vector2(300,-100));

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
        enemsPos.get(L2).add(new Vector2(-150,180));

        enemsPos.get(L2).add(new Vector2(750,300));
        enemsPos.get(L2).add(new Vector2(450,180));
        enemsPos.get(L2).add(new Vector2(150,300));


        enemsPos.get(L3).add(new Vector2(-750,180));
        enemsPos.get(L3).add(new Vector2(-450,300));
        enemsPos.get(L3).add(new Vector2(-150,180));

        enemsPos.get(L3).add(new Vector2(150,400));
        enemsPos.get(L3).add(new Vector2(450,180));
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

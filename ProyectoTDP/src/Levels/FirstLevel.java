package Levels;


import DataParsers.ILevelDataParser;
import DataParsers.ParsersManager;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipDirector;
import Rewards.Reward;

import java.util.Collection;

public final class FirstLevel extends AbstractLinkedLevel
{
    private Collection<EnemyShip> enemies;
    private Collection<Reward> rewards;
    private ILevelDataParser parser;
    private EnemyShipDirector director;


    public FirstLevel(){
        parser = ParsersManager.getInstance().getLevelDataParser();
        parser.setKey("level1");
    }

    @Override
    public void assembleLevel() {

    }
    @Override
    public void startLevel() {

    }



    @Override
    public boolean completed() {
        return false;
    }
}

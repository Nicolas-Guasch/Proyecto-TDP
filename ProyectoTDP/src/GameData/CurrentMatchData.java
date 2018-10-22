package GameData;

import Broadcaster.*;
import Entities.Ships.PlayerShip;
import Levels.AbstractLevel;
import Levels.LevelsManager;
import Mementos.IMementoPlayer;

public class CurrentMatchData
{

    public IBroadcaster<Integer> OnScoreChanges;
    private Invoker<Integer> invokerScoreChanges;

    private static CurrentMatchData instance;
    private int score =0;
    //private int lifes = 5;
    private GameMemento checkPoint;

/*
*     private void setCheckpoint(){
        IMementoPlayer memplayer = PlayerShip.getInstance().makeMemento();
        AbstractLevel lev = LevelsManager.getInstance().currentLevel();
        checkPoint = new GameMemento(lev,memplayer);
    }

    private void loadCheckpoint(){
//        LevelsManager.getInstance().moveTo(checkPoint.getLevel());
        PlayerShip.getInstance().loadMemento(checkPoint.getPlayerStatus());
    }*/

    public static CurrentMatchData getMatchData()
    {
        if(instance == null)
        {
            instance = new CurrentMatchData();
        }
        return instance;
    }

    private CurrentMatchData()
    {
        BroadcasterPackage<Integer> pack = ObserverSystem.getInstance().GetBroadcaster();
        invokerScoreChanges = pack.Invoker;
        OnScoreChanges = pack.Broadcaster;

    }




    public int currentScore()
    {
        return score;
    }

    public void incScore(int cant)
    {
        score += cant;
        invokerScoreChanges.Invoke(score);
    }



    public void resetScore()
    {
        score = 0;
        invokerScoreChanges.Invoke(score);
    }



}


package GameData;

import Observer.*;

public class CurrentMatchData
{

    public IBroadcaster<Integer> OnScoreChanges;
    private Invoker<Integer> invokerScoreChanges;

    private static CurrentMatchData instance;
    private int score =0;


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
        observerPack<Integer> pack = ObserverSystem.getInstance().getBroadcaster();
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
        invokerScoreChanges.invoke(score);
    }



    public void resetScore()
    {
        score = 0;
        invokerScoreChanges.invoke(score);
    }



}


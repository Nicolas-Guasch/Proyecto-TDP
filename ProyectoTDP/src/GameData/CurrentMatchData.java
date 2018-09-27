package GameData;

import Broadcaster.*;

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
        BroadcasterPackage<Integer> pack = GetBroadcaster.GetBroadcaster();
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


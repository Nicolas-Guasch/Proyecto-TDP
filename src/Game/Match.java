package Game;

public class Match
{

    private int score = 0;

    public void addScore(int x){
        score+=x;
    }

    public int getScore() {
        return score;
    }

    public void resetScore()
    {score = 0;}
}

package Game;

import java.util.concurrent.SubmissionPublisher;
// que carajo es SubmissionPublisher ???????
public class Partida extends SubmissionPublisher {

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

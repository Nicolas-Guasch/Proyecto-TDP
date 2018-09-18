
package GameData;

import Entities.Reaper;
import UI.UI;

public class GameOver
{
    private static GameOver instance;

    public static GameOver getInstance()
    {
        if(instance == null)
        {
            instance = new GameOver();
        }
        return instance;
    }
    private GameOver(){}

    public void MakeGameOver()
    {
        SoundManager.Instance().gameOver();
        UI.getInstance().gameOver();
        Reaper.Instance().KillThemAll();
    }


}

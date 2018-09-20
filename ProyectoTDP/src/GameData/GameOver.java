
package GameData;

import Engine.EngineGetter;
import Entities.TheGrimReaper;
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
        TheGrimReaper.Instance().KillThemAll();
        EngineGetter.Instance().get().WaitForFrames(()->System.exit(0),500);
    }


}


package GameData;

import Engine.EngineGetter;
import Engine.While;
import Entities.TheGrimReaper;
import UI.UI;

public class LostOrWin
{
    private static LostOrWin instance;

    public static LostOrWin getInstance()
    {
        if(instance == null)
        {
            instance = new LostOrWin();
        }
        return instance;
    }
    private LostOrWin(){}

    public void MakeYouWin()
    {
        SoundManager.Instance().YouWin();
        UI.getInstance().win();
        EngineGetter.Instance().get().WaitForFrames(()->System.exit(0),500);
    }

    public void MakeGameOver()
    {
        SoundManager.Instance().gameOver();
        UI.getInstance().gameOver();
        TheGrimReaper.Instance().KillThemAll();
        new While(()->true,() -> TheGrimReaper.Instance().KillThemAll()).Excecute();
        EngineGetter.Instance().get().WaitForFrames(()->System.exit(0),500);
    }


}

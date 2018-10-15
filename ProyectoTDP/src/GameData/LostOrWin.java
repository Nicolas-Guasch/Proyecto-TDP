package GameData;

import Audio.SoundManager;
import Engine.EngineGetter;
import Engine.While;
import Entities.TheGrimReaper;
import UI.UI;

public class LostOrWin implements ILostOrWin{

    private boolean made;

    public LostOrWin()
    {
        made = false;
    }

    public void AllianceWins()
    {
        if (!made) {
            SoundManager.Instance().YouWin();
            UI.getInstance().win();
            EngineGetter.Instance().get().waitForFrames(() -> System.exit(0), 500);
        }
        made = true;
    }

    public void EmpireWins()
    {
        if (!made) {
            SoundManager.Instance().gameOver();
            UI.getInstance().gameOver();
            TheGrimReaper.Instance().KillThemAll();
            new While(() -> true, () -> TheGrimReaper.Instance().KillThemAll()).Excecute();
            EngineGetter.Instance().get().waitForFrames(() -> System.exit(0), 500);
        }
        made = true;
    }


}

package GameData;

import Audio.SoundManager;
import Engine.EngineGetter;
import Engine.While;
import Entities.EveryOne;
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
            EngineGetter.Instance().get().waitForFrames(this::creditsAndClose, 450);
        }
        made = true;
    }


    private void creditsAndClose()
    {
        EngineGetter.Instance().get().waitForFrames(() -> System.exit(0), 60*25);
        UI.getInstance().startLevelByString("credits",2);
    }

    public void EmpireWins()
    {
        if (!made) {
            SoundManager.Instance().gameOver();
            UI.getInstance().gameOver();
            EveryOne.getInstance().killThemAll();
            new While(() -> true, () -> EveryOne.getInstance().killThemAll()).Excecute();
            EngineGetter.Instance().get().waitForFrames(this::creditsAndClose, 450);

        }
        made = true;
    }


}

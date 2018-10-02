package ParaTestear;

import Engine.*;
import GameData.GameSettings;
import GameData.SoundManager;

import Levels.LevelTester;
import Misc.DeathStar;
import RenderingSystem.RenderingTools;
import RenderingSystem.Window;

public class Tester
{
    public static void main(String[] a) {

        Window window = Window.GetInstance();
        IEngine eng = EngineGetter.Instance().get();
        window.AddInput(eng.Pauser());//para poner pausa

        LevelTester.Instance().runLevel();
        //new DeathStar().get();


        eng.SuscribeToUpdate(window);
        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }

}

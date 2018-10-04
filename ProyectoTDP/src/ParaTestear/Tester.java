package ParaTestear;

import Engine.*;
import GameData.GameSettings;
import GameData.LostOrWin;
import GameData.SoundManager;

import Levels.LevelA;
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


        var level = new LevelA();
        level.run(Tester::ganar, Tester::perder);
        //new DeathStar().get();


        eng.SuscribeToUpdate(window);
        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }

    private static void ganar() {
        LostOrWin.getInstance().MakeYouWin();
        System.out.println("ganaste");
    }

    private static void perder() {
        System.out.println("perdiste");
    }

}

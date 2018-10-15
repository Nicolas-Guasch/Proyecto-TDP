package ParaTestear;

import Engine.*;
import GameData.LostOrWin;
import Audio.SoundManager;

import TesterLevels.LevelA;
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


        eng.suscribeToUpdate(window);
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

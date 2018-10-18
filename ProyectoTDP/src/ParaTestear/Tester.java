package ParaTestear;

import Engine.*;
import GameData.MatchResult;
import Audio.SoundManager;

import Levels.GameManager;
import TesterLevels.LevelA;
import RenderingSystem.Window;

public class Tester
{
    public static void main(String[] a) {

        Window window = Window.GetInstance();
        IEngine eng = EngineGetter.Instance().get();
        window.AddInput(eng.Pauser());//para poner pausa


        GameManager.getInstance().StartGame();


        eng.suscribeToUpdate(window);
        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }

    private static void ganar() {
        MatchResult.getInstance().AllianceWins();
        System.out.println("ganaste");
    }

    private static void perder() {
        System.out.println("perdiste");
    }

}

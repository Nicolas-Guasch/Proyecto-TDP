package Game;

import Engine.*;
import Audio.SoundManager;

import GameTimeLine.GameManager;

import RenderingSystem.Window;
import RenderingSystem.Background;

public class TheGame
{
    public static void startGame() {


        Window window = Window.GetInstance();
        IEngine eng = EngineGetter.Instance().get();
        window.AddInput(eng.Pauser());//para poner pausa

        GameManager.getInstance().StartGame();
        eng.suscribeToUpdate(window);

        SoundManager.Instance().ImperialMarchPlay();
        window.Show();

        var bb = Background.getInstance();
        bb.setSpeedBackground(13f);

        eng.start();
    }



}

package ParaTestear;

import Engine.*;
import Entities.EveryOne;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.Pusher;
import GameData.MatchResult;
import Audio.SoundManager;

import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteKeyInput;
import Levels.GameManager;

import RenderingSystem.Window;
import RenderingSystem.Background;

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



        var bb = Background.getInstance();
        bb.setSpeedBackground(0.1f);



        AbstractDiscreteInput presY = new DiscreteKeyInput("yY");

        presY.OnAction().Suscribe((b)->{
            EveryOne.getInstance().takeLazyVisitor(new Pusher(500,
                PlayerShip.getInstance().referenced().
                        transform().position()));

        });




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

package ParaTestear;

import ADTs.Vector2;
import Engine.*;
import GameData.MatchResult;
import Audio.SoundManager;

import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteKeyInput;
import Levels.GameManager;

import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;
import UI.Grill;

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

        Renderizable r1 = new Renderizable(new SpriteData("miniship"));
        Renderizable r2 = new Renderizable(new SpriteData("miniship"));
        Renderizable r3 = new Renderizable(new SpriteData("miniship"));
        Renderizable r4 = new Renderizable(new SpriteData("miniship"));
        Renderizable r5 = new Renderizable(new SpriteData("miniship"));
        Renderizable[] rs = {r1,r2,r3,r4,r5};

        for (Renderizable r : rs) {
            GameObject.getRoot().addChild().addComponent(r);
        }

        AbstractDiscreteInput presY = new DiscreteKeyInput("yY");

        Grill g = new Grill(new Vector2(-230,230),new Vector2(80,80),3);
        var ref = new Object() {
            int i = 0;
        };
        presY.OnAction().Suscribe((b)->{
            if(ref.i >= 5)return;
            g.add(rs[ref.i]);
            ref.i++;
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

package ParaTestear;

import Engine.Core;
import Engine.GameObject;
import Engine.IEngine;
import Engine.Vector2;
import GameData.GameSettings;
import GameData.SoundManager;
import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteKeyInput;
import Levels.LevelOne;
import Misc.DeathStar;
import RenderingSystem.RenderingTools;
import RenderingSystem.Window;

public class Tester
{
    public static void main(String[] a) {

        Window window = Window.GetInstance();
        IEngine eng = Engine.EngineFactory.Instance().get();

        window.AddInput(eng.Pauser());//para poner pausa

        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();


        LevelOne.Instance().runLevel();

        new DeathStar().get();

        /*

        GameObject deathStar = GameObject.getRoot().addChild();
        deathStar.addComponent(new MirrorBounds(topRight.prod(1.3f), bottomLeft.prod(1.3f)));
        deathStar.addComponent(new AlwaysLateral(Vector2.Random(0.8f)));
        deathStar.getTransform().setZcomponent(3);
        deathStar.addComponent(new Renderizable(new SpriteData(Paths.DeathStar))).Show();

*/

        //TODO : Acordarme de siempre darle show() a los Renderizables

        // ------ suscribo la raiz de objetos y la ventana al engine
        eng.SuscribeToUpdate(GameObject.getRoot());
        eng.SuscribeToPhysicsUpdate(GameObject.getRoot());
        eng.SuscribeToUpdate(window);

        //---------- Cosas de testing -----------

        AbstractDiscreteInput testTree = new DiscreteKeyInput("pP");
        testTree.OnAction().Suscribe((b)->{
            System.out.println("Last retard: "+ Core.lastRetard);
            System.out.println("Objects: "+GameObject.getRoot().Size());
            System.out.println("Components: "+GameObject.getRoot().SizeComps());
        });


        // -------- muestro la ventana y arranco el motor -------

        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }

}

package ParaTestear;

import Engine.*;
import GameData.GameSettings;
import GameData.SoundManager;
import Levels.LevelOne;
import Misc.DeathStar;
import RenderingSystem.RenderingTools;
import RenderingSystem.Window;

public class Tester
{
    public static void main(String[] a) {

        Window window = Window.GetInstance();
        IEngine eng = EngineGetter.Instance().get();

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

        eng.SuscribeToUpdate(window);

        //---------- Cosas de testing -----------




        // -------- muestro la ventana y arranco el motor -------

        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }

}

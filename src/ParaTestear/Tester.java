package ParaTestear;

import Engine.Components.RectangleCollider;
import Engine.Vector2;
import Engine.GameObject;
import Engine.IEngine;
import GameData.GameSettings;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;
import UtilsBehaviours.MouseFollower;

import java.awt.*;
import java.util.Random;

public class Tester
{
    public static void main(String[]a)
    {
        System.out.println(Paths.Background);
        Window window = Window.GetInstance();
        IEngine eng = Engine.EngineFactory.Instance().get();


        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();


        // --------- Crear a Solo --------
        GameObject solo = GameObject.getRoot().addChild();
        solo.addComponent(new MouseFollower());
        solo.addComponent(new MirrorBounds(topRight,bottomLeft));
        solo.getTransform().setZcomponent(-8);
        solo.addComponent(new ComportamientoTester());
        Renderizable rendSolo = new Renderizable(new SpriteData(Paths.Alcon,new Vector2(100,100)));
        solo.addComponent(rendSolo);
        rendSolo.Show();
        solo.getTransform().setPosition(new Vector2(0,-250));



        // ------- Crear a Vader --------

        GameObject vader = GameObject.getRoot().addChild();

        vader.addComponent(new Renderizable(new SpriteData(Paths.Vader,new Vector2(200,200)))).Show();
        vader.addComponent(new ComportamientoKamikazee(solo.getTransform(),2));
        vader.getTransform().setZcomponent(-9);
        vader.addComponent(new StormTheFront(60,40, 30));
        vader.getTransform().setPosition(new Vector2(0,2900));

        // --------- Crear un par de chavoncitos que se mueven random ---------

        for(int i=0; i<10 ; i++)
        {
            GameObject chavon = GameObject.getRoot().addChild();
            chavon.addComponent(new Renderizable(new SpriteData(Paths.TIE,new Vector2(200,200)))).Show();
            chavon.addComponent(new ComportamientoNormal());
            chavon.addComponent(new StormTheFront(3*new Random(i).nextInt(50),5, 20));
            chavon.getTransform().setPosition(new Vector2(i*100-500,250 + (i%2==0?100:0)));
            chavon.getTransform().setTop(Vector2.DOWN());
            chavon.getTransform().setZcomponent(-7);
            chavon.addCollider(new RectangleCollider(new Vector2(100,100)));
            chavon.addComponent(new DeathIfTouchHazardous());
        }


        //--------- Crear la DeathStar ----------
        GameObject deathStar = GameObject.getRoot().addChild();
        deathStar.getTransform().setPosition(new Vector2(-500,400));
        Renderizable death = new Renderizable(new SpriteData(Paths.DeathStar,new Vector2(500,500)));
        death.Show();
        deathStar.addComponent(death);
        deathStar.addComponent(new MirrorBounds(topRight.prod(1.4f),bottomLeft.prod(1.4f)));
        deathStar.addComponent(new AlwaysLateral(Vector2.Random()));
        deathStar.addComponent(new AlwaysRotate(0.04f));
        deathStar.getTransform().setZcomponent(20);



        //TODO : Acordarme de siempre darle show() a los Renderizables

        // ------ suscribo la raiz de objetos y la ventana al engine
        eng.SuscribeToUpdate(GameObject.getRoot());
        eng.SuscribeToPhysicsUpdate(GameObject.getRoot());
        eng.SuscribeToUpdate(window);

        // -------- muestro la ventana y arranco el motor -------

        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }
}

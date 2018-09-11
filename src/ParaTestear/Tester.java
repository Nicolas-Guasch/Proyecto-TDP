package ParaTestear;

import Engine.Components.RectangleCollider;
import Engine.Core;
import Engine.Vector2;
import Engine.GameObject;
import Engine.IEngine;
import GameData.GameSettings;
import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteKeyInput;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;
import Ships.Builder.ShipIndustry;
import Ships.Builder.SoloShipBuilder;
import Ships.Entities.Ship;
import Stuff.Paths;
import UtilsBehaviours.MirrorBounds;
import UtilsBehaviours.MouseFollower;

import java.util.Random;

public class Tester
{
    public static void main(String[]a) {
        System.out.println(Paths.Background);
        Window window = Window.GetInstance();
        IEngine eng = Engine.EngineFactory.Instance().get();


        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);

        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();


        // --------- Crear a Solo --------


        ShipIndustry industry = new ShipIndustry();
        industry.setBuilder(new SoloShipBuilder());
        industry.assembleShip();
        Ship soloShip = industry.getShip();
        GameObject solo = soloShip.getReferenced();
        solo.getTransform().setPosition(new Vector2(0, -250));

/*
        GameObject solo = GameObject.getRoot().addChild();
        solo.addComponent(new MouseFollower());
        solo.addComponent(new MirrorBounds(topRight, bottomLeft));

        solo.addComponent(new ComportamientoTester());
        Renderizable rendSolo = new Renderizable(new SpriteData(Paths.Alcon, new Vector2(100, 100)));
        solo.addComponent(rendSolo);
        rendSolo.Show();
        solo.getTransform().setPosition(new Vector2(0, -250));
    */

        Normal(1,topRight,bottomLeft,solo);
        Normal(2,topRight,bottomLeft,solo);
        Normal(3,topRight,bottomLeft,solo);
        Normal(4,topRight,bottomLeft,solo);
        Normal(5,topRight,bottomLeft,solo);
        Normal(6,topRight,bottomLeft,solo);
        Normal(7,topRight,bottomLeft,solo);

        CreateMareado(4,topRight,bottomLeft);
        CreateMareado(3,topRight,bottomLeft);
        Kamik(4,solo,topRight,bottomLeft);
        Kamik(2,solo,topRight,bottomLeft);
        Kamik(1,solo,topRight,bottomLeft);
        Kamik(3,solo,topRight,bottomLeft);
        Kamik(5,solo,topRight,bottomLeft);
        Kamik(6,solo,topRight,bottomLeft);
        Kamik(7,solo,topRight,bottomLeft);
        Kamik(8,solo,topRight,bottomLeft);



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

    private static void CreateMareado(int i, Vector2 topRight, Vector2 bottomLeft) {
        GameObject chavon = GameObject.getRoot().addChild();
        chavon.addComponent(new Renderizable(new SpriteData(Paths.TIE2,new Vector2(200,200)))).Show();
        chavon.addComponent(new Mareado(1));
        chavon.addComponent(new AlwaysRotate(0.01f));
        chavon.addComponent(new DummyShooters(new Random(i).nextInt(50),5, 20));
        chavon.getTransform().setPosition(new Vector2(i*100-500,250 + (i%2==0?100:0)));
        chavon.getTransform().setTop(Vector2.DOWN());
        chavon.getTransform().setZcomponent(-7);
        chavon.addCollider(new RectangleCollider(new Vector2(40,40)));
        chavon.addComponent(new DeathIfTouchHazardous());
        chavon.addComponent(new MirrorBounds(topRight,bottomLeft));
        //if(new Random().nextBoolean())
        //chavon.setOnDestroy(()->CreateMareado(new Random().nextInt(20),topRight,bottomLeft));
    }

    private static void Normal(int i, Vector2 topRight, Vector2 bottomLeft, GameObject solo)
    {
        GameObject chavon = GameObject.getRoot().addChild();
        chavon.addComponent(new Renderizable(new SpriteData(Paths.TIE,new Vector2(200,200)))).Show();
        chavon.addComponent(new ComportamientoNormal());
        chavon.addComponent(new DummyShooters(3*new Random(i).nextInt(50),5, 20));
        chavon.getTransform().setPosition(new Vector2(i*100-500,250 + (i%2==0?100:0)));
        chavon.getTransform().setTop(Vector2.DOWN());
        chavon.getTransform().setZcomponent(-7);
        chavon.addCollider(new RectangleCollider(new Vector2(40,40)));
        chavon.addComponent(new ChangeIfTouchHazardous(new ComportamientoKamikazee(solo.getTransform(), 4)));

        chavon.addComponent(new MirrorBounds(topRight,bottomLeft));
        //if(new Random().nextBoolean())
        //chavon.setOnDestroy(()->Normal(new Random().nextInt(20),bottomLeft,topRight));
    }

    private static void Kamik(int i, GameObject solo, Vector2 bottomLeft, Vector2 topRight)
    {
        GameObject kamikaz = GameObject.getRoot().addChild();
        kamikaz.addComponent(new Renderizable(new SpriteData(Paths.Vader, new Vector2(200, 200)))).Show();
        kamikaz.addComponent(new ComportamientoKamikazee(solo.getTransform(), 4));
        kamikaz.getTransform().setZcomponent(-9);
        kamikaz.addComponent(new StormTheFront(60, 40, 30));
        kamikaz.getTransform().setPosition(new Vector2(i*500-1000, 400));
        kamikaz.addCollider(new RectangleCollider(new Vector2(80, 80)));
        kamikaz.addComponent(new DeathIfTouchHazardous());
        kamikaz.addComponent(new MirrorBounds(topRight,bottomLeft));
       // if(new Random().nextBoolean())
        //kamikaz.setOnDestroy(()->Kamik(new Random().nextInt(5),solo, bottomLeft, topRight));
    }
}

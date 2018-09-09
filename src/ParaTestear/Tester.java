package ParaTestear;

import Engine.Vector2;
import Engine.GameObject;
import Engine.IEngine;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import RenderingSystem.Window;
import UtilsBehaviours.MouseFollower;

public class Tester
{
    public static void main(String[]a)
    {
        System.out.println(Paths.Background);
        Window window = Window.GetInstance();
        IEngine eng = Engine.EngineFactory.Instance().get();


        // --------- Crear a Solo --------
        GameObject solo = GameObject.getRoot().addChild();
        solo.getTransform().setTop(new Vector2(2,1));
        solo.getTransform().setZcomponent(-8);
        solo.getTransform().setPosition(new Vector2(-400,-400));
        ComportamientoTester c = new ComportamientoTester();

        Renderizable rendSolo = new Renderizable(new SpriteData(Paths.Alcon,new Vector2(100,100)));
        solo.addComponent(rendSolo);
        rendSolo.Show();
        solo.addComponent(c);


        // ------- Crear a Vader --------

        GameObject vader = GameObject.getRoot().addChild();
        vader.getTransform().setPosition(new Vector2(10,10));
        vader.addComponent(new Renderizable(new SpriteData(Paths.Vader,new Vector2(250,250)))).Show();
        vader.addComponent(new ComportamientoKamikazee(solo.getTransform(),2));
        vader.getTransform().setZcomponent(-5);
        vader.addComponent(new StormTheFront(60,40));


        // --------- Crear un par de chavoncitos que se mueven random ---------





        //--------- Crear la DeathStar ----------
        GameObject deathStar = GameObject.getRoot().addChild();
        deathStar.getTransform().setPosition(new Vector2(-500,400));
        Renderizable death = new Renderizable(new SpriteData(Paths.DeathStar,new Vector2(500,500)));
        death.Show();
        deathStar.addComponent(death);
        deathStar.addComponent(new AlwaysLateral(new Vector2(1,0.05f)));



        //TODO : Acordarme de siempre darle show() a los Renderizables

        // ------ suscribo la raiz de objetos y la ventana al engine
        eng.SuscribeToUpdate(GameObject.getRoot());
        eng.SuscribeToUpdate(window);

        // -------- muestro la ventana y arranco el motor -------

        SoundManager.Instance().ImperialMarchPlay();
        window.Show();
        eng.Start();
    }
}

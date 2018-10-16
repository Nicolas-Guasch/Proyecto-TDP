package Misc;

import ADTs.Vector2;
import ADTs.Vector3;
import Engine.Component;
import Engine.GameObject;
import GameData.GameSettings;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.Directionable;
import SoundSystem.internal.UpdateRunner;
import Tools.Random;
import UtilsBehaviours.MirrorBounds;

import java.util.Collection;
import java.util.LinkedHashSet;


public class Stars extends Component
{

    private static final int MaxStarsOnScreen = 400;

    private static int cantLModelsStars = 5;
    private static int cantModelsStars = 11;

    private static Collection<GameObject> stars;
    private int i=0;

    private Stars(){
        stars = new LinkedHashSet<>();
    }

    private static void getBack() {
        GameObject star = instance.addChild();
        Renderizable rend = new Renderizable(new SpriteData("bstar"+ Random.value(1,cantLModelsStars+1)));
        rend.show();
        star.setRenderer(rend);
        star.addComponent(new Directionable(Vector2.DOWN(Random.value(35,45))));
        star.addComponent(new StarBeh(Random.value(200,600)));
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        star.addComponent(new MirrorBounds(topRight.prod(1.4f + Random.value(10,90)/90f), bottomLeft.prod(1.4f)));

        var tr = star.transform();
        tr.setTop(Vector2.Random());
        int ancho = GameSettings.GetInstance().sizeWindow.width+150;
        tr.setPosition(Vector3.Get(Random.value(-ancho/2,ancho/2),-900,15));
        stars.add(star);
    }

    private static void getFront()
    {

        GameObject star = instance.addChild();
        Renderizable rend = new Renderizable(new SpriteData("star"+ Random.value(1,cantModelsStars+1)));
        rend.show();
        star.setRenderer(rend);
        star.addComponent(new Directionable(Vector2.DOWN(Random.value(20,30))));
        star.addComponent(new StarBeh(Random.value(200,900)));
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        star.addComponent(new MirrorBounds(topRight.prod(1.4f + Random.value(10,90)/90f), bottomLeft.prod(1.4f)));

        var tr = star.transform();
        tr.setTop(Vector2.Random());
        int ancho = GameSettings.GetInstance().sizeWindow.width+150;
        tr.setPosition(Vector3.Get(Random.value(-ancho/2,ancho/2),-900,-15));
        stars.add(star);
    }


    private static GameObject instance;
    public static void instanceOne(){
        if(instance != null) return;
        var s = new Stars();
        instance = GameObject.getRoot().addChild();
        instance.addComponent(s);
    }

    @Override
    public void Update() {
        if(MaxStarsOnScreen < stars.size()) return;
        if(i>=Random.value(2,40))
        {
            i=0;
            if(Random.value(0,20)<15)
                getBack();
                else
            getFront();
        }
        i++;
    }


}

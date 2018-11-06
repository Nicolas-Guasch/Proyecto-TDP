package RenderingSystem;

import ADTs.Vector2;
import Engine.EngineGetter;
import GameData.GameSettings;
import Tools.Random;

import java.awt.*;

public class RenderingTools
{

    private static float screenShakeLevel = 0;
    private static float h = GameSettings.GetInstance().sizeWindow.height;
    private static float w = GameSettings.GetInstance().sizeWindow.width;

    //el mundito mide 1000 x 600
    //width es x , height es y
    public static Vector2 CanvasToWorld(Dimension d)
    {
        if(screenShakeLevel!=0) {
            return new Vector2((d.width-w/2) + (Random.value()-Random.value()) * screenShakeLevel,((h/2)-d.height) + (Random.value()-Random.value()) * screenShakeLevel);
        }
        return new Vector2(d.width-w/2,(h/2)-d.height);

    }

    public static Dimension WorldToCanvas(Vector2 vec)
    {
        if(screenShakeLevel!=0){
            return Randomize(new Dimension((int)(vec.x()+w/2),(int)((h/2)-vec.y())));
        }
        return new Dimension((int)(vec.x()+w/2),(int)((h/2)-vec.y()));
    }

    private static Dimension Randomize(Dimension dimension) {
        Dimension d = dimension;
        d.width += (new java.util.Random().nextInt(10)-10 )* screenShakeLevel;
        d.height += (new java.util.Random().nextInt(10)-10 )* screenShakeLevel;
        return d;
    }

    private static boolean onShake = false;
    public static void doScreenShake(float level){
        if(onShake)return;
        screenShakeLevel = level;
        onShake = true;
        EngineGetter.Instance().get().waitForFrames(RenderingTools::stopshake,15);
    }

    private static void stopshake() {
        screenShakeLevel = 0;
        onShake = false;
    }
}

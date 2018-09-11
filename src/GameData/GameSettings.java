package GameData;

import Engine.Vector2;
import RenderingSystem.RenderingTools;
import UtilsBehaviours.Bounds;

import java.awt.*;

public class GameSettings
{
    // Singleton
    private static GameSettings instance;


    public static GameSettings GetInstance()
    {
        if(instance==null)
        {
            instance = new GameSettings();
        }
        return instance;
    }
    private GameSettings(){
        //recordar que hay que
        //levantar un archivo con las settings aqui
    }



    //--------------------------------

    // ------ Configurations -----
    public final short FPS = 60;
    public final Color background = new Color(191, 175, 22);
    public final Dimension sizeWindow = new Dimension(1280,739);


    public Bounds bounds()
    {
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        return new Bounds(topRight,bottomLeft);
    }
}

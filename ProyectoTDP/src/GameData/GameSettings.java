package GameData;

import Engine.Vector2;
import Entities.EntityData;
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
        //TODO:
        //recordar que hay que
        //levantar un archivo con las settings aqui
    }



    //--------------------------------

    // ------ Configurations -----
    public final short FPS = 60;
    public final Color UIbgColor = new Color(35, 103, 191);
    public final Dimension sizeWindow = new Dimension(1280,739);


    public Bounds bounds()
    {
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        return new Bounds(topRight,bottomLeft);
    }

    // --------- Entities Data ----------------

    public final float TieBulletSpeed = 10;
    public final float TieSpeed = 2;
    public final float FirstBossSpeed = 12;
    public final float SoloBulletSpeed = 12;
    public final float SoloSpeed = 20;



    public final EntityData PlayerData = new EntityData(100,150,7);
    public final EntityData TieData = new EntityData(100,25,0);
    public final EntityData TieBulletData = new EntityData(1,15,0);
    public final EntityData SoloBulletData = new EntityData(1,10,0);
    public final EntityData FirstBossData = new EntityData(800,1500,50);





}

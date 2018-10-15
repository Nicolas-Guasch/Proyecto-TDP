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

    private ISettingsParser parser;


    private GameSettings(){
        //TODO:
        //recordar que hay que
        //levantar un archivo con las settings aqui
        parser = new PlaceHolderSettingsParser();
        PlayerData = parser.getEntityData(ParseEntityData.PlayerData);
        TieData = parser.getEntityData(ParseEntityData.TieData);
        TieBulletData = parser.getEntityData(ParseEntityData.TieBulletData);
        SoloBulletData = parser.getEntityData(ParseEntityData.SoloBulletData);
        FirstBossData = parser.getEntityData(ParseEntityData.FirstBossData);


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



    public final EntityData PlayerData ;
    public final EntityData TieData ;
    public final EntityData TieBulletData ;
    public final EntityData SoloBulletData ;
    public final EntityData FirstBossData ;

}

enum ParseEntityData{
    PlayerData,
    TieData,
    TieBulletData,
    SoloBulletData,
    FirstBossData,
}

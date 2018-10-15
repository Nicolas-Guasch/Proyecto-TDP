package GameData;

import Engine.Vector2;
import Entities.EntityData;
import RenderingSystem.RenderingTools;
import UtilsBehaviours.Rect;

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

        parser = new PlaceHolderSettingsParser();


        PlayerData = parser.getEntityData(EntityEnum.PlayerData);
        TieData = parser.getEntityData(EntityEnum.TieData);
        TieBulletData = parser.getEntityData(EntityEnum.TieBulletData);
        SoloBulletData = parser.getEntityData(EntityEnum.SoloBulletData);
        FirstBossData = parser.getEntityData(EntityEnum.FirstBossData);




    }



    //--------------------------------

    // ------ Configurations -----
    public final short FPS = 60;
    public final Dimension sizeWindow = new Dimension(1280,739);


    public Rect bounds()
    {
        Vector2 bottomRight = RenderingTools.CanvasToWorld(sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        return new Rect(topRight,bottomLeft);
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



enum EntityEnum {
    PlayerData,
    TieData,
    TieBulletData,
    SoloBulletData,
    FirstBossData,
}

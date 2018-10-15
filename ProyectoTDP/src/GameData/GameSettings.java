package GameData;

import ADTs.Rect;
import Engine.Vector2;
import Entities.EntityData;
import RenderingSystem.RenderingTools;


import java.awt.*;

public class GameSettings
{
    private static GameSettings instance;
    public static GameSettings GetInstance()
    {
        instance = instance==null?new GameSettings():instance;
        return instance;
    }

    public final float TieBulletSpeed = 10;
    public final float TieSpeed = 2;
    public final float FirstBossSpeed = 12;
    public final float PlayerBulletSpeed = 12;

    private ISettingsParser parser;
    public final short FPS = 60;
    public final Dimension sizeWindow = new Dimension(1280,739);



    // --------- Entities Data ----------------
    public final EntityData PlayerData;
    public final EntityData TieData;
    public final EntityData TieBulletData;
    public final EntityData SoloBulletData;
    public final EntityData FirstBossData;

    private GameSettings()
    {
        parser = new PlaceHolderSettingsParser();

        PlayerData = parser.getEntityData(EntityEnum.PlayerData);
        TieData = parser.getEntityData(EntityEnum.TieData);
        TieBulletData = parser.getEntityData(EntityEnum.TieBulletData);
        SoloBulletData = parser.getEntityData(EntityEnum.SoloBulletData);
        FirstBossData = parser.getEntityData(EntityEnum.FirstBossData);



    }

    public Rect bounds()
    {
        Vector2 bottomRight = RenderingTools.CanvasToWorld(sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        return new Rect(topRight,bottomLeft);
    }

}



enum EntityEnum {
    PlayerData,
    TieData,
    TieBulletData,
    SoloBulletData,
    FirstBossData,
}

enum FloatEnum{

    TieBulletSpeed,
    TieSpeed,
    FirstBossSpeed,
    PlayerBulletSpeed,
}

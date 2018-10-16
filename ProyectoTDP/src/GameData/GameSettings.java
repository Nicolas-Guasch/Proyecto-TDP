package GameData;

import ADTs.Rect;
import ADTs.Vector2;
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


    private ISettingsParser parser;

    // ---------- general settings -----------
    public final short FPS; // must be 60
    public final Dimension sizeWindow;

    // --------- floats ----------
    public final float
            TieBulletSpeed,
            TieSpeed,
            FirstBossSpeed,
            PlayerBulletSpeed;

    // --------- Entities Data ----------------
    public final EntityData
            PlayerData,
            TieData,
            TieBulletData,
            SoloBulletData,
            FirstBossData;

    private GameSettings()
    {
        parser = new PlaceHolderSettingsParser();

        PlayerData = parser.getEntityData(EntityEnum.PlayerData);
        TieData = parser.getEntityData(EntityEnum.TieData);
        TieBulletData = parser.getEntityData(EntityEnum.TieBulletData);
        SoloBulletData = parser.getEntityData(EntityEnum.SoloBulletData);
        FirstBossData = parser.getEntityData(EntityEnum.FirstBossData);

        TieBulletSpeed = parser.getFloat(FloatEnum.TieBulletSpeed);
        TieSpeed = parser.getFloat(FloatEnum.TieSpeed);
        FirstBossSpeed = parser.getFloat(FloatEnum.FirstBossSpeed);
        PlayerBulletSpeed = parser.getFloat(FloatEnum.PlayerBulletSpeed);

        sizeWindow = parser.sizeWindow();
        FPS = (short) parser.FPS();
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

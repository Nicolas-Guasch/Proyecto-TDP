package GameData;

import ADTs.Rect;
import ADTs.Vector2;
import DataParsers.ParsersManager;
import Entities.EntityData;
import RenderingSystem.RenderingTools;


import java.awt.*;

public class GameSettings
{

    public static final int DrunkIntensity = 1;
    public static boolean DrunkMode = false;
    public static int difficulty=1;

    private static GameSettings instance;
    public final EntityData PlayerInitialData;

    public static GameSettings GetInstance()
    {
        if (null == instance) {
            instance = new GameSettings();
        }
        return instance;
    }


    private ISettingsParser parser;

    // ---------- general settings -----------
    public final short FPS; // must be 60
    public Dimension sizeWindow;

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
            PlayerBulletData,
            FirstBossData;

    private GameSettings()
    {
        parser = ParsersManager.getInstance().getSettingsParser();

        PlayerData = parser.getEntityData(EntityEnum.PlayerData);
        PlayerInitialData = PlayerData.clone();
        TieData = parser.getEntityData(EntityEnum.TieData);
        TieBulletData = parser.getEntityData(EntityEnum.TieBulletData);
        PlayerBulletData = parser.getEntityData(EntityEnum.PlayerBulletData);
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

    public EntityData PlaceHolderData() {
        return EntityData.WithEqualsValues(1);
    }

}



enum EntityEnum {
    PlayerData,
    TieData,
    TieBulletData,
    PlayerBulletData,
    FirstBossData,
}

enum FloatEnum{

    TieBulletSpeed,
    TieSpeed,
    FirstBossSpeed,
    PlayerBulletSpeed,
}

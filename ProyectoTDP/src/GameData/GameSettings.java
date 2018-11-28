package GameData;

import ADTs.IVector2;
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

    // ---------- general settings -----------
    public final short FPS;
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

        PlayerData = new EntityData(2500,12,0.3f);
        PlayerInitialData = PlayerData.clone();
        TieData = new EntityData(150,100,0);
        TieBulletData =new EntityData(10,25,0);
        PlayerBulletData = new EntityData(1,8,0);
        FirstBossData = new EntityData(700,10000,0.2f);

        TieBulletSpeed =8;
        TieSpeed = 1;
        FirstBossSpeed = 10;
        PlayerBulletSpeed = 12;

        sizeWindow = new Dimension(1280,739);
        FPS = 70;
    }

    public Rect bounds()
    {
        IVector2 bottomRight = RenderingTools.CanvasToWorld(sizeWindow);
        IVector2 topRight = bottomRight.mirrorX();
        IVector2 bottomLeft = bottomRight.mirrorY();
        return new Rect(topRight,bottomLeft);
    }

    public EntityData PlaceHolderData() {
        return EntityData.WithEqualsValues(1);
    }

}
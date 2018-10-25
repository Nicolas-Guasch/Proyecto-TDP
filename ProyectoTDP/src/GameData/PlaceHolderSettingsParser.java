package GameData;

import Entities.EntityData;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class PlaceHolderSettingsParser implements ISettingsParser
{

    // ---------- configurations -----------
    private final short FPS = 60;
    private final Dimension sizeWindow = new Dimension(1330,689);

    private final EntityData
            PlayerData = new EntityData(1500, 8,0.2f),
            PlayerBulletData = new EntityData(1,5,0),
            TieData = new EntityData(100,25,0),
            TieBulletData = new EntityData(10,15,0),
            FirstBossData = new EntityData(1500,10000,0.5f);


    private final float
            TieBulletSpeed = 10,
            TieSpeed = 2,
            FirstBossSpeed = 12,
            PlayerBulletSpeed = 14;



    private final Map<EntityEnum,EntityData> entities;
    private final Map<FloatEnum,Float> floats;

    public PlaceHolderSettingsParser()
    {
        entities = new TreeMap<>();
        floats = new TreeMap<>();

        initializeEntity();
        initializeFloats();
    }

    private void initializeFloats()
    {
        floats.put(FloatEnum.TieBulletSpeed,TieBulletSpeed);
        floats.put(FloatEnum.TieSpeed,TieSpeed);
        floats.put(FloatEnum.FirstBossSpeed,FirstBossSpeed);
        floats.put(FloatEnum.PlayerBulletSpeed,PlayerBulletSpeed);
    }

    private void initializeEntity() {

        entities.put(EntityEnum.PlayerData,PlayerData);
        entities.put(EntityEnum.TieData,TieData);
        entities.put(EntityEnum.TieBulletData,TieBulletData);
        entities.put(EntityEnum.PlayerBulletData, PlayerBulletData);
        entities.put(EntityEnum.FirstBossData,FirstBossData);
    }

    @Override
    public EntityData getEntityData(EntityEnum ref)
    {
        return entities.get(ref);
    }

    @Override
    public float getFloat(FloatEnum ref) {
        return floats.get(ref);
    }

    @Override
    public int FPS() {
        return FPS;
    }

    @Override
    public Dimension sizeWindow() {
        return sizeWindow;
    }
}

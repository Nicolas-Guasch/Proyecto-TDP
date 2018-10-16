package GameData;

import Entities.EntityData;

import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class PlaceHolderSettingsParser implements ISettingsParser
{

    // ---------- configurations -----------
    private final short FPS = 60;
    private final Dimension sizeWindow = new Dimension(1280,739);



    private final EntityData
            PlayerData = new EntityData(500000,150,70),
            TieData = new EntityData(100,25,0),
            TieBulletData = new EntityData(1,15,0),
            SoloBulletData = new EntityData(1,10,0),
            FirstBossData = new EntityData(800000,150,5000);


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
        entities.put(EntityEnum.SoloBulletData,SoloBulletData);
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

package GameData;

import Entities.EntityData;

import java.util.Map;
import java.util.TreeMap;

public class PlaceHolderSettingsParser implements ISettingsParser
{
    private final EntityData PlayerData = new EntityData(5000,150,7);
    private final EntityData TieData = new EntityData(100,25,0);
    private final EntityData TieBulletData = new EntityData(1,15,0);
    private final EntityData SoloBulletData = new EntityData(1,10,0);
    private final EntityData FirstBossData = new EntityData(800,1500,50);

    private Map<EntityEnum,EntityData> mapeo;

    public PlaceHolderSettingsParser()
    {
        mapeo = new TreeMap<>();
        mapeo.put(EntityEnum.PlayerData,PlayerData);
        mapeo.put(EntityEnum.TieData,TieData);
        mapeo.put(EntityEnum.TieBulletData,TieBulletData);
        mapeo.put(EntityEnum.SoloBulletData,SoloBulletData);
        mapeo.put(EntityEnum.FirstBossData,FirstBossData);
    }

    @Override
    public EntityData getEntityData(EntityEnum ref)
    {
        return mapeo.get(ref);
    }
}

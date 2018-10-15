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

    private Map<ParseEntityData,EntityData> mapeo;

    public PlaceHolderSettingsParser()
    {
        mapeo = new TreeMap<>();
        mapeo.put(ParseEntityData.PlayerData,PlayerData);
        mapeo.put(ParseEntityData.TieData,TieData);
        mapeo.put(ParseEntityData.TieBulletData,TieBulletData);
        mapeo.put(ParseEntityData.SoloBulletData,SoloBulletData);
        mapeo.put(ParseEntityData.FirstBossData,FirstBossData);
    }

    @Override
    public EntityData getEntityData(ParseEntityData ref)
    {
        return mapeo.get(ref);
    }
}

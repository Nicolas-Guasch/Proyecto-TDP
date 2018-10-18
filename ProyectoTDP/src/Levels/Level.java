package Levels;


import ADTs.Vector2;
import ADTs.Vector3;
import DataParsers.ILevelDataParser;
import DataParsers.ParsersManager;
import Engine.Components.Transform;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.EnemyShipDirector;
import Entities.Ships.PlayerShip;
import Entities.EveryOne;
import EntitiesVisitor.EnemiesCounter;
import EntitiesVisitor.VisitorEntity;
import EntitiesVisitor.WeaponEnemiesSwitch;
import Rewards.Reward;
import Rewards.RewardFactory;
import Rewards.RewardKey;


import java.util.*;

public final class Level extends AbstractLinkedLevel
{

    private Collection<Reward> rewards;
    private ILevelDataParser parser;
    private EnemyShipDirector director;

    //Requiere player ya instanciado
    Level(int number){
        parser = ParsersManager.getInstance().getLevelDataParser();
        parser.setKey("level"+number);
        director = new EnemyShipDirector();
        if(number<=3){
            nextLevel = new Level(number+1);
        }
    }

    private EnemyShip getShip(){
        director.create();
        director.assemble();
        return director.get();
    }

    @Override
    public void assembleLevel() {
        assembleEnemies();
    }


    private <Type> Type getRandom(List<Type> c)
    {
        int r = Math.abs(new Random().nextInt(c.size()));
        return c.subList(r,c.size()-1).iterator().next();
        //TODO: revisar si abarca toda la lista
    }

    private Runnable ThrowAReward(RewardKey key, Transform tr){
        return ()-> RewardFactory.getInstance().getReward(key,tr);
    }

    private void assembleEnemies()
    {
        List<EnemyShipBuilder> builders = parser.enemies();
        Iterator<RewardKey> itRewards = parser.rewards().iterator();
        List<Vector2> positions = parser.enemiesPositions();
        Collections.shuffle(positions);
        int z = 10;
        var weaponDisabler = new WeaponEnemiesSwitch(false);

        for(Vector2 v : positions)
        {
            director.setBuilder(getRandom(builders));
            Vector3 pos =  v.v3(z);
            var ship = getShip();
            ship.referenced().transform().setPosition(pos);
            EveryOne.getInstance().add(ship);
            if(itRewards.hasNext()){
                var onDeath =ThrowAReward(itRewards.next(),ship.referenced().transform());
                ship.setOnDeath(onDeath);
            }
            ship.accept(weaponDisabler);
        }
    }

    @Override
    public void startLevel()
    {
        VisitorEntity weaponEnabler = new WeaponEnemiesSwitch(true);
        EveryOne.getInstance().forEach(weaponEnabler);
        PlayerShip.getInstance().referenced().transform().setPosition(new Vector3(0,300,-90));
    }


    @Override
    public boolean completed() {
        //si se pone ineficiente hago una lista de enemigos y tiro un stream
        EnemiesCounter counter = new EnemiesCounter();
        EveryOne.getInstance().forEach(counter);
        return counter.getCount()==0;
    }
}

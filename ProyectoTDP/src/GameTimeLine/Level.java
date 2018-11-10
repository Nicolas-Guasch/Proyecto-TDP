package GameTimeLine;


import ADTs.*;
import DataParsers.*;
import Engine.Components.*;
import Entities.Builders.Directors.BarricadeBothDirector;
import Entities.Builders.Directors.BarricadeEnemDirector;
import Entities.Barricades.BlueBarricadeMaker;
import Entities.Barricades.OrangeBarricadeMaker;
import Entities.Ships.*;
import Entities.*;
import EntitiesVisitor.*;
import GameData.GameSettings;
import Rewards.*;
import Scripts.HyperSpace;
import Scripts.Jumper;


import java.util.*;

public final class Level extends TimePoint
{

    private Collection<Entity> rewards;
    private ILevelsData parser;
    private EnemyShipDirector director;
    private BarricadeBothDirector directorBboth;
    private BarricadeEnemDirector directorBenem;
    private int number;
    private boolean levelRunning = false;


    //Requiere player ya instanciado
    Level(int number){
        parser = ParsersManager.getInstance().getLevelDataParser();
        parser.setKey("level"+number);
        director = new EnemyShipDirector();
        directorBboth = new BarricadeBothDirector();
        directorBenem = new BarricadeEnemDirector();
        directorBboth.setBuilder(new BlueBarricadeMaker());
        directorBenem.setBuilder(new OrangeBarricadeMaker());
        this.number = number;
    }


    private BaseEnemyShip getShip(){
        director.create();
        director.assemble();
        return director.get();
    }

    @Override
    public void assembleMoment() {

        assembleEnemies();
        assembleBarricades();


    }

    private Entities.Entity getBarricadeRandom(){
        if(Math.abs(new Random().nextInt())%8>2){
            directorBboth.create();
            directorBboth.assemble();
            return directorBboth.get();
        }
        else{
            directorBenem.create();
            directorBenem.assemble();
            return directorBenem.get();
        }
    }

    private static boolean swapbarricade = true;

    private void assembleBarricades() {
        for(Vector2 v : parser.obstaclesPositions())
        {
            Entities.Entity e = getBarricadeRandom();
            e.referenced().transform().setPosition(v.v3(2));
            swapbarricade = !swapbarricade;
            e.referenced().transform().setTop(swapbarricade ? Vector2.DOWN() : Vector2.UP());

            e.referenced().getRenderer().show();
            EveryOne.getInstance().add(e);
        }
    }


    private <Type> Type getRandom(List<Type> c)
    {
        Collections.shuffle(c);
        return c.get(new Random().nextInt(c.size()));
    }

    private Runnable ThrowAReward(RewardKey key, Transform tr){
        return ()-> RewardsManager.getInstance().getReward(key,tr);
    }

    private void assembleEnemies()
    {
        List<EnemyShipBuilder> builders = parser.enemies();
        Iterator<RewardKey> itRewards = parser.rewards().iterator();
        List<Vector2> positions = parser.enemiesPositions();
        for (int j = 0; j < GameSettings.difficulty-1; j++) {
            positions.addAll(new LinkedList<>(positions));
        }
        Collections.shuffle(positions);
        int z = 10;
        var vsiz = GameSettings.GetInstance().sizeWindow;
        Vector3 far = new Vector3(vsiz.width/1.9f,vsiz.height/1.9f,z);
        int i = 0;
        for(Vector2 v : positions)
        {
            BaseEnemyShip ship;
            director.setBuilder(getRandom(builders));
            ship = getShip();
            i+=15;

            {
                Vector3 pos =  v.v3(z);
                ship.referenced().transform().setPosition(far);
                Jumper jumper = HyperSpace.Jump(ship.referenced().transform(),pos.xy(),30,i);
                ship.getArsenal().setActive(false);
                jumper.getOnComplete().suscribe(new ShipAction(ship,(s)->s.getArsenal().setActive(true)));
                EveryOne.getInstance().add(ship);

            }
            if(itRewards.hasNext()){
                var onDeath =ThrowAReward(itRewards.next(),ship.referenced().transform());
                ship.setOnDeath(onDeath);
            }

        }



    }

    @Override
    public void startMoment()
    {




        levelRunning = true;
    }

    @Override
    public boolean completed() {
        //si se pone ineficiente hago una lista de enemigos y tiro un stream
        EnemiesCounter counter = new EnemiesCounter();
        EveryOne.getInstance().takeVisitor(counter);


        return counter.getCount()==0 && levelRunning;
    }

    @Override
    public void clean() {
        LinkedList<EnemyShip> cosas = new LinkedList<>();
        VisitorEntity collector = new GetEnemies(cosas);
        EveryOne.getInstance().takeVisitor(collector);
        cosas.forEach(e->e.data().setHealth(-1));
    }
}

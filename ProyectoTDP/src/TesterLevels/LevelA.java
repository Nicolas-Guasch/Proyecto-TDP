package TesterLevels;

import ADTs.Vector3;
import Engine.Component;
import Engine.Components.Transform;
import Engine.EngineGetter;
import Engine.GameObject;
import ADTs.Vector2;
import Entities.Builders.Directors.ObstacleBidirectionalDirector;
import Entities.Builders.Directors.ObstacleMonoDirectionalDirector;
import Entities.Obstacles.NaveDuraObstacle;
import Entities.Obstacles.NaveViejaImperioMaker;
import Entities.Rewards.RewardFactory;
import Entities.Ships.*;
import Entities.Ships.EnemiesBuilders.FastTieMaker;
import Entities.Ships.EnemiesBuilders.VaderTieMaker;
import Entities.Ships.EnemiesBuilders.WhiteTieMaker;

import Entities.TheGrimReaper;
import Audio.SoundManager;
import IAs.*;
import InputManager.DirectionalMouse;
import InputManager.DirectionalWASD;
import Misc.DeathStar;
import Tools.Random;

import java.util.ArrayList;
import java.util.Collection;

public class LevelA extends Component implements Level
{

    private float z = -10; // z component for enemies;
    private Runnable onWin, onLoose;

    private EnemiesStructure enemies;
    EnemyShipDirector enemiesDirector;
    EnemyShipBuilder[] currentBuilder;
    EnemyShipBuilder currentBuilderBoss;

    boolean bossRunning;

    private boolean running;
    private Collection<Vector3> initial_positions;


    private PlayerShip player;

    public LevelA()
    {
        running = false;
        enemies = EnemiesManager.getInstance().getStructure();
        enemiesDirector = new EnemyShipDirector();
        initialize();
        gameObjectStuff();
        bossRunning = false;
    }

    private void gameObjectStuff()
    {
        GameObject.getRoot().addChild().addComponent(this);
        setActive(false);
    }

    //TODO: hacer robusto por si tiene errores el string
    private Collection<Vector3> parsePositions(String posits)
    {
        ArrayList<Vector3> ret = new ArrayList<>();
        ret.ensureCapacity(posits.length()/2);
        String[] pares = posits.split(" ");
        for(var par : pares)
        {
            String[] xy = par.split(",");
            float x = Float.parseFloat(xy[0]);
            float y = Float.parseFloat(xy[1]);
            ret.add(new Vector3(x,y,z));
        }
        return ret;
    }

    private void initialize()
    {
        String positions = "-300,100 -100,150 100,200 300,150 " +
                           "-300,200 -100,300 100,100 300,300 "+
                           "-300,300 -100,250 100,0 300,250"; //TODO: levantar de archivo

        currentBuilder = new EnemyShipBuilder[]{new WhiteTieMaker(),
                                                new FastTieMaker()};

        currentBuilderBoss = new VaderTieMaker();

        enemiesDirector.setBuilder(currentBuilder[0]);
        initial_positions = parsePositions(positions);
        new DeathStar().get();

    }

    @Override
    public void run(Runnable onWin, Runnable onLoose)
    {
        if(!PlayerShip.isInitialited())
        initializePlayer();

        Runnable action = ()->
        {
            setActive(true);
            running = true;
            this.onLoose = onLoose;
            this.onWin = onWin;

            createEnemies();
            createBarricades();
        };
        EngineGetter.Instance().get().waitForFrames(action,480);
    }

    private void createBarricades() {
        ObstacleMonoDirectionalDirector monodi = new ObstacleMonoDirectionalDirector();
        monodi.setBuilder(new NaveViejaImperioMaker());
        monodi.create();
        monodi.assemble();
        var obstaculo = monodi.get();
        obstaculo.getReferenced().getTransform().setPosition(new Vector3(90,-100,-90));
        TheGrimReaper.Instance().add(obstaculo);

        ObstacleBidirectionalDirector bid = new ObstacleBidirectionalDirector();
        bid.setBuilder(new NaveDuraObstacle());

        BidirectionalAt(Vector3.Get(230,-80,-90),Vector2.UP(),bid);
        BidirectionalAt(Vector3.Get(-230,-80,-90),Vector2.UP(),bid);
        BidirectionalAt(Vector3.Get(430,180,-90),Vector2.LEFT(),bid);
        BidirectionalAt(Vector3.Get(-430,180,-90),Vector2.RIGHT(),bid);


    }

    private void BidirectionalAt(Vector3 point, Vector2 top,ObstacleBidirectionalDirector director)
    {
        director.create();
        director.assemble();
        var bidobs2 = director.get();
        bidobs2.getReferenced().getTransform().setPosition(point);
        bidobs2.getReferenced().getTransform().setTop(top);
        TheGrimReaper.Instance().add(bidobs2);
    }


    private void initializePlayer()
    {
        var pdir = new PlayerShipDirector();
        pdir.setBuilder(new PlayerShipMaker());
        pdir.create();
        pdir.assemble();
        UI.UI.getInstance().startLevel(0);
        player = pdir.get();
        player.getReferenced().getTransform().setPosition(new Vector3(0, -300,-20));

        TheGrimReaper.Instance().add(player);
    }
    private void createEnemies()
    {
        int setRew = Random.value(3,7);
        int i=0;
        for(var pos : initial_positions)
        {
            i++;
            enemiesDirector.setBuilder(currentBuilder[i%currentBuilder.length]);
            setRew--;
            enemiesDirector.create();
            enemiesDirector.assemble();
            EnemyShip ship = enemiesDirector.get();
            enemies.addEnemy(ship);
            ship.getReferenced().getTransform().setPosition(pos);
            TheGrimReaper.Instance().add(ship);
            if(setRew==2)
            {
                Transform t = ship.getReferenced().getTransform();
                ship.setDoOnDeath(()-> RewardFactory.getWeaponIceReward(t));
            }
            if(setRew==0)
            {
                Transform t = ship.getReferenced().getTransform();
                ship.setDoOnDeath(()-> RewardFactory.getWeaponReward(t));
            }
        }
    }

    @Override
    public boolean isRunning()
    {
        return running;
    }

    @Override
    public void Update()
    {
        if(!running) return;

        if(enemies.remaining() <= 0)
        {
            if(bossRunning)
            {
                onWin.run();
                setActive(false);
                return;
            }
            else
            {
                enableWS();
                runBoss();
                return;
            }
        }
        if(!player.alive())
        {
            onLoose.run();
            setActive(false);
        }
    }

    private void enableWS() {

        var handler = player.getPilot().getHandler();
        DirectionalMouse direction = new DirectionalMouse(player.getReferenced().getTransform());
        DirectionalWASD move = new DirectionalWASD();
        handler = new PlayerMove(handler,move,direction);
        handler = new Slippery(handler);
        player.getPilot().setHandler(handler);

    }

    private void runBoss()
    {
        bossRunning = true;
        setActive(false);

        Runnable musicStuff = ()->{
            SoundManager.Instance().VaderBreath();
            SoundManager.Instance().ImperialMarchStop();

        };
        EngineGetter.Instance().get().waitForFrames(musicStuff,120);

        Runnable makeTheBoss = ()->{
            setActive(true);
            enemiesDirector.setBuilder(currentBuilderBoss);
            enemiesDirector.create();
            enemiesDirector.assemble();
            EnemyShip ship = enemiesDirector.get();
            enemies.addEnemy(ship);
            ship.getReferenced().getTransform().setPosition(new Vector3(0,400,z));
            TheGrimReaper.Instance().add(ship);
        };
        EngineGetter.Instance().get().waitForFrames(makeTheBoss,120);

    }

    @Override
    public void destroy()
    {
        gameObject().Destroy();
    }
}

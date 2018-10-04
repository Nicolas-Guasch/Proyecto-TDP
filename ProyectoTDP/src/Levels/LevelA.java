package Levels;

import Engine.Component;
import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Rewards.RewardFactory;
import Entities.Ships.*;
import Entities.Ships.EnemiesBuilders.FastTieMaker;
import Entities.Ships.EnemiesBuilders.VaderTieMaker;
import Entities.Ships.EnemiesBuilders.WhiteTieMaker;

import Entities.TheGrimReaper;
import IAs.*;
import InputManager.DirectionalMouse;
import InputManager.DirectionalWASD;
import Misc.DeathStar;
import Tools.Random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Handler;

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

        currentBuilder = new EnemyShipBuilder[2];
        currentBuilder[0] = new WhiteTieMaker();
        currentBuilder[1] = new FastTieMaker();
        currentBuilderBoss = new VaderTieMaker();

        enemiesDirector.setBuilder(currentBuilder[0]);
        initial_positions = parsePositions(positions);
        new DeathStar().get();

    }

    @Override
    public void run(Runnable onWin, Runnable onLoose)
    {
        setActive(true);
        running = true;
        this.onLoose = onLoose;
        this.onWin = onWin;
        if(!PlayerShip.isInitialited())
            initializePlayer();
        createEnemies();
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
            enemiesDirector.setBuilder(currentBuilder[i%2]);
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
        enemiesDirector.setBuilder(currentBuilderBoss);
        enemiesDirector.create();
        enemiesDirector.assemble();
        EnemyShip ship = enemiesDirector.get();
        enemies.addEnemy(ship);
        ship.getReferenced().getTransform().setPosition(new Vector3(0,300,z));
        TheGrimReaper.Instance().add(ship);
    }

    @Override
    public void destroy()
    {
        gameObject().Destroy();
    }
}

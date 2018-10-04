package Levels;

import Engine.Component;
import Engine.GameObject;
import Entities.Rewards.RewardFactory;
import Entities.Ships.*;
import Entities.Ships.EnemiesBuilders.WhiteTieMaker;

import Entities.TheGrimReaper;
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
    EnemyShipBuilder currentBuilder;
    private boolean running;
    private Collection<Vector3> initial_positions;


    private PlayerShip player;

    public LevelA()
    {
        running = false;
        enemies = new EnemiesList();
        enemiesDirector = new EnemyShipDirector();
        initialize();
        gameObjectStuff();

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
                           "-300,200 -100,300 100,100 300,300" ; //TODO: levantar de archivo

        currentBuilder = new WhiteTieMaker();
        enemiesDirector.setBuilder(currentBuilder);
        initial_positions =parsePositions(positions);

    }




    @Override
    public void run(Runnable onWin, Runnable onLoose)
    {
        setActive(true);
        running = true;
        this.onLoose = onLoose;
        this.onWin = onWin;
        initializePlayer();
        createEnemies();
        new DeathStar().get();
    }

    private void initializePlayer()
    {
        var pdir = new PlayerShipDirector();
        pdir.setBuilder(new PlayerShipMaker());
        pdir.create();
        pdir.assemble();

        player = pdir.get();
        player.getReferenced().getTransform().setPosition(new Vector3(0, -300,-20));

        TheGrimReaper.Instance().add(player);
    }
    private void createEnemies()
    {
        int setRew = Random.value(2,4);
        for(var pos : initial_positions)
        {
            setRew--;
            enemiesDirector.create();
            enemiesDirector.assemble();
            EnemyShip ship = enemiesDirector.get();
            enemies.addEnemy(ship);
            ship.getReferenced().getTransform().setPosition(pos);
            TheGrimReaper.Instance().add(ship);
            if(setRew==0)
            {
                ship.setDoOnDeath(RewardFactory::getWeaponReward);
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
            onWin.run();
            setActive(false);
        }
        if(!player.alive())
        {
            onLoose.run();
            setActive(false);
        }
    }

    @Override
    public void destroy()
    {
        gameObject().Destroy();
    }
}

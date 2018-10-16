package TesterLevels;

import ADTs.Vector2;
import Engine.While;
import Entities.Builders.AbstractRewardFactory;
import Entities.Ships.EnemyShipDirector;
import Entities.Ships.EnemyShip;
import Entities.Rewards.Reward;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class LinedWave extends AbstractWave
{
    Collection<EnemyShip> enemies;
    Queue<EnemyShip> death;
    Collection<Reward> rewards;
    Runnable onComplete;
    private boolean running = false;

    private Map<EnemyShip, Vector2> ubications;


    private static float xmax = -600;
    private static float xmin = 500;
    private static float ycoord = 300;
    private static Vector2 hyperspace = new Vector2(200,200);

    public LinedWave()
    {
        enemies = new LinkedList<>();
        rewards = new LinkedList<>();
        ubications = new HashMap<>();
        death = new LinkedBlockingQueue<>();
    }



    @Override
    public void run() {
        running = true;
        enemies.forEach(this::MoveToPosition);
        ubications.clear();
        While wh = new While(this::Condition,this::Action);
        wh.Excecute();
    }

    private boolean Condition() {
        return enemies.size()<=0;
    }
    // borrar seguramente
    public void Action()
    {
        enemies.forEach(e->{if(e.getData().getHealth()<=0)death.add(e);});
        while(!death.isEmpty())
        {
            enemies.remove(death.remove());
        }
    }

    @Override
    public Iterable<EnemyShip> addEnemies(EnemyShipDirector director, int cant) {
        float phaseShift = (xmax-xmin)/cant;
        var x = xmin;
        for(int i=0 ; i<cant ; i++)
        {
            director.create();
            director.assemble();
            x += phaseShift;
            var enemy = director.get();
            enemy.getReferenced().transform().setPosition(hyperspace);
            ubications.put(enemy,new Vector2(x,ycoord));
            enemies.add(enemy);
        }
        return enemies;
    }

    @Override
    public void addReward(AbstractRewardFactory rewards) {

    }

    @Override
    public boolean EmptyWave()
    {
        Action();
        return enemies.isEmpty();
    }


    private void MoveToPosition(EnemyShip en)
    {
        if(!ubications.containsKey(en))
            new Exception("-- error en wave -- ").printStackTrace();
        en.getReferenced().transform().DoMove(
                ubications.get(en), 5
        );
    }
}

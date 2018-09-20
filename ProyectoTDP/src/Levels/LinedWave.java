package Levels;

import Engine.Vector2;
import Engine.While;
import Entities.Builders.AbstractRewardFactory;
import Entities.Builders.Directors.EnemyShipDirector;
import Entities.EnemyShip;
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
    private static Vector2 hyperspace = new Vector2(0,800);

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
        float phaseshift = (xmax-xmin)/cant;
        for(int i=0 ; i<cant ; i++)
        {
            director.create();
            director.assemble();
            var x = xmin+ phaseshift*i;
            var enemy = director.get();
            enemy.getReferenced().getTransform().setPosition(hyperspace);
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
        en.getReferenced().getTransform().DoMove(
                ubications.get(en), 5
        );
    }
}

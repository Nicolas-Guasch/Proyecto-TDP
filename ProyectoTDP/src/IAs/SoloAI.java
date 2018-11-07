package IAs;

import ADTs.Vector2;
import Entities.Entity;
import Entities.EveryOne;
import Entities.Ships.EnemyShip;
import EntitiesVisitor.GetEnemies;
import EntitiesVisitor.VisitorEntity;

import java.util.*;

public class SoloAI extends AIQueryDecorator {

    private final Vector2[] waypoints;
    private List<EnemyShip> enemies;
    private int max = 5;
    private int i=0;
    private int index_waypoints=0;

    public SoloAI(EntityQuery decorated, Vector2[] waypoints) {
        super(decorated);
        enemies = new ArrayList<>();
        this.waypoints = waypoints;
    }

    public void searchSomeEnemies(int cant){
        VisitorEntity getEnemies = new GetEnemies(enemies);
        for (Iterator<Entity> iterator = EveryOne.getInstance().getEntities().iterator(); iterator.hasNext(); ) {
            Entity e = iterator.next();
            e.accept(getEnemies);
            if (enemies.size() >= max) {
                break;
            }
        }
    }

    private Vector2 getTarget(Entity ent){
        searchSomeEnemies(2); //se que es ineficiente :P
        EnemyShip ship;
        if(enemies.size()<=0){
            return Vector2.UP();
        }
        ship = enemies.get(0);
        return ship.referenced().transform().position();
    }

    private Vector2 getToMove(Vector2 pos){

        if(waypoints[index_waypoints].distanceTo(pos)<30 && waypoints.length>index_waypoints+1){
            index_waypoints++;
        }

        return waypoints[index_waypoints].sub(pos);
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        return getToMove(ent.referenced().transform().position()).norma();
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return getTarget(ent).sub(ent.referenced().transform().position());
    }
}

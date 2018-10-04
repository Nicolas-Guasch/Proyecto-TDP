package Levels;

import Entities.Entity;
import Entities.Ships.EnemyShip;

import java.util.*;
import java.util.stream.Collectors;

public class EnemiesList extends EnemiesStructure {

    private Collection<EnemyShip> enemies;

    public EnemiesList() {
        enemies = new HashSet<>();
    }


    @Override
    public Iterator<EnemyShip> iterator() {
        return enemies.iterator();
    }

    @Override
    public void addEnemy(EnemyShip enemy) {
        enemies.add(enemy);
    }

    @Override
    public int remaining() {
        //cuenta la cantidad que cumplen con la condicion alive
        return enemies.stream().filter(Entity::alive).collect(Collectors.toList()).size();
    }
}

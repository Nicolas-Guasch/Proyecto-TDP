package Levels;

import Entities.Ships.EnemyShip;

import java.util.Collection;
import java.util.Iterator;

//TODO: borrar Wave
public abstract class EnemiesStructure implements Iterable<EnemyShip>
{

    public abstract void addEnemy(EnemyShip enemy);
    public abstract int remaining();

}

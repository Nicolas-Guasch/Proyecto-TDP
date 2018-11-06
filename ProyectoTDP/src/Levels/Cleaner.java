package Levels;

import Entities.Entity;
import Entities.EveryOne;
import Entities.Ships.EnemyShip;
import EntitiesVisitor.GetEnemies;
import EntitiesVisitor.GetEnemiesAndBarricades;
import EntitiesVisitor.VisitorEntity;

import java.util.LinkedList;

public class Cleaner extends AbstractLevel {
    @Override
    public void assembleLevel() {

    }

    @Override
    public void startLevel() {

    }

    @Override
    public boolean completed() {
        return true;
    }

    @Override
    public void clean() {
        LinkedList<Entity> cosas = new LinkedList<>();
        VisitorEntity collector = new GetEnemiesAndBarricades(cosas);
        EveryOne.getInstance().takeVisitor(collector);
        cosas.forEach(e->e.data().setHealth(-1));
    }
}

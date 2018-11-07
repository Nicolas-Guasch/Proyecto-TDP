package GameTimeLine;

import Entities.Entity;
import Entities.EveryOne;
import EntitiesVisitor.GetEnemiesAndBarricades;
import EntitiesVisitor.VisitorEntity;

import java.util.LinkedList;

public class Cleaner extends TimePoint {
    @Override
    public void assembleMoment() {

    }

    @Override
    public void startMoment() {

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

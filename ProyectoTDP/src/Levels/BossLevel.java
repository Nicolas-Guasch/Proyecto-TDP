package Levels;

import ADTs.Vector2;
import ADTs.Vector3;
import Entities.Builders.Concretes.FirstBossBuilder;
import Entities.Entity;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.EnemiesBuilders.VaderTieMaker;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipDirector;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.GetEnemiesAndBarricades;
import EntitiesVisitor.VisitorEntity;
import UI.ShipStatus;
import UI.UI;

import java.util.LinkedList;

public class BossLevel extends AbstractLevel {

    private EnemyShipDirector director;
    private boolean playing=false;
    EnemyShip vader;
    public BossLevel(){
        director = new EnemyShipDirector();
        director.setBuilder(new VaderTieMaker());
    }


    @Override
    public void assembleLevel() {

        director.create();
        director.assemble();
        vader = director.get();
        EveryOne.getInstance().add(vader);
        vader.referenced().transform().setPosition(new Vector3(0,100,1));

    }

    @Override
    public void startLevel() {
        var ShipStatus = new ShipStatus(new Vector2(100,200), vader.observerHealth(),"bossbar",vader.data().getHealth());
        UI.getInstance().addUIComponent(ShipStatus);

    }

    @Override
    public boolean completed() {
        return playing && !vader.alive();
    }

    @Override
    public void clean() {
        vader.setData(EntityData.WithEqualsValues(-1));
    }
}

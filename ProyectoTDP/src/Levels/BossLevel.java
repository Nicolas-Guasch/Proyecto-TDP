package Levels;

import ADTs.Vector2;
import ADTs.Vector3;
import Engine.EngineGetter;
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
import GameData.MatchResult;
import UI.ShipStatus;
import UI.UI;

import java.util.LinkedList;

public class BossLevel extends AbstractLevel {

    private ShipStatus ShipStatus;
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
        ShipStatus = new ShipStatus(new Vector2(100,200), vader.observerHealth(),"bossbar",vader.data().getHealth());
        UI.getInstance().addUIComponent(ShipStatus);
        EngineGetter.Instance().get().waitForFrames(()->playing = true,3);
    }

    @Override
    public boolean completed() {
        var ret = playing && !vader.alive();
        if(ret){
            MatchResult.getInstance().AllianceWins();

        }
        return ret;

    }

    @Override
    public void clean() {
        vader.setData(EntityData.WithEqualsValues(-1));
        UI.getInstance().removeUIComponent(ShipStatus);
    }
}
package Levels;

import ADTs.Vector2;
import ADTs.Vector3;
import Engine.EngineGetter;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.EnemiesBuilders.VaderAMaker;
import Entities.Ships.EnemiesBuilders.VaderBMaker;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.EnemyShipDirector;
import GameData.MatchResult;
import UI.ShipStatus;
import UI.UI;

public class BossLevel extends AbstractLevel {

    private ShipStatus ShipStatus;
    private EnemyShipDirector director;
    private boolean playing=false;
    EnemyShip vader;
    public BossLevel(EnemyShipBuilder builder){
        director = new EnemyShipDirector();
        director.setBuilder(builder);
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
        return ret;

    }

    @Override
    public void clean() {
        vader.setData(EntityData.WithEqualsValues(-1));
        System.out.println("BossLevel::clean");
        UI.getInstance().removeUIComponent(ShipStatus);
    }
}

package GameTimeLine;

import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.EntityData;
import Entities.EveryOne;
import Entities.Ships.BaseEnemyShip;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.EnemyShipDirector;
import Entities.Ships.Player.PlayerShip;
import Tools.Random;
import UI.ShipStatus;
import UI.UI;

public class BossLevel extends TimePoint {

    private ShipStatus ShipStatus;
    private EnemyShipDirector director;
    private boolean playing=false;
    BaseEnemyShip vader;
    public BossLevel(EnemyShipBuilder builder){
        director = new EnemyShipDirector();
        director.setBuilder(builder);
    }


    @Override
    public void assembleMoment() {

        director.create();
        director.assemble();
        vader = director.get();
        EveryOne.getInstance().add(vader);
    }

    @Override
    public void startMoment() {
        ShipStatus = new ShipStatus(new Vector2(100,200), vader.observerHealth(),"bossbar",vader.data().getHealth());
        vader.referenced().transform().setPosition(Vector2.random(Random.value(300,450)).sum(PlayerShip.getInstance().referenced().transform().position()));
        UI.getInstance().addUIComponent(ShipStatus);
        EngineGetter.Instance().get().waitForFrames(()->playing = true,100);
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

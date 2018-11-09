package GameTimeLine;

import ADTs.Vector3;
import Engine.EngineGetter;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.VisitorEntity;
import EntitiesVisitor.WeaponSwitch;
import GameData.GameSettings;

public class PlayerAssembler extends TimePoint {
    private boolean ready=false;

    @Override
    public void assembleMoment() {
        EveryOne.getInstance().add(PlayerShip.getInstance());
        var y = GameSettings.GetInstance().sizeWindow.height*(3.5f/8);
        var vec = new Vector3(0,-y,-90);

        PlayerShip.getInstance().referenced().transform().setPosition(vec);
    }

    @Override
    public void startMoment() {

        var weaponDisabler = new WeaponSwitch(false);
        EveryOne.getInstance().takeLazyVisitor(weaponDisabler);
        VisitorEntity weaponEnabler = new WeaponSwitch(true);
        EngineGetter.Instance().get().waitForFrames(()->EveryOne.getInstance().takeLazyVisitor(weaponEnabler),30);
        EngineGetter.Instance().get().waitForFrames(()-> ready =true,120);
    }

    @Override
    public boolean completed() {
        return ready;
    }
}

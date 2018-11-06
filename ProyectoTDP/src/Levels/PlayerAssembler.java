package Levels;

import ADTs.Vector3;
import Engine.EngineGetter;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.VisitorEntity;
import EntitiesVisitor.WeaponSwitch;

public class PlayerAssembler extends TimePoint {
    private boolean ready=false;

    @Override
    public void assembleMoment() {
        EveryOne.getInstance().add(PlayerShip.getInstance());
        PlayerShip.getInstance().referenced().transform().setPosition(new Vector3(0,-200,-90));
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

package Levels;

import ADTs.Vector3;
import Engine.EngineGetter;
import Entities.EveryOne;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.VisitorEntity;
import EntitiesVisitor.WeaponSwitch;

public class PlayerAssembler extends AbstractLevel {
    private boolean ready=false;

    @Override
    public void assembleLevel() {
        EveryOne.getInstance().add(PlayerShip.getInstance());
        PlayerShip.getInstance().referenced().transform().setPosition(new Vector3(0,-100,-90));
    }

    @Override
    public void startLevel() {

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

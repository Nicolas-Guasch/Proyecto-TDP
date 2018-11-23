package GameTimeLine;

import ADTs.Vector3;
import Engine.Action;
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
        float y = GameSettings.GetInstance().sizeWindow.height*(3.5f/8);
        Vector3 vec = new Vector3(0,-y,-90);

        PlayerShip.getInstance().referenced().transform().setPosition(vec);
    }

    @Override
    public void startMoment() {

        WeaponSwitch weaponDisabler = new WeaponSwitch(false);
        EveryOne.getInstance().takeLazyVisitor(weaponDisabler);
        VisitorEntity weaponEnabler = new WeaponSwitch(true);
        EngineGetter.Instance().get().waitForFrames(new Action() {
            @Override
            public void invoke() {
                EveryOne.getInstance().takeLazyVisitor(weaponEnabler);
            }
        },30);
        EngineGetter.Instance().get().waitForFrames(this::setReady,120);
    }

    @Override
    public boolean completed() {
        return ready;
    }

    private void setReady() {//fixme
        ready = true;
    }
}

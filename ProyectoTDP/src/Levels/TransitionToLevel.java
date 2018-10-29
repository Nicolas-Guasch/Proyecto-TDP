package Levels;

import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.Ships.PlayerShip;
import RenderingSystem.Background;
import UI.UI;

public class TransitionToLevel extends AbstractLevel {

    private String nameBackground;
    private int levelIndex;
    private boolean ready = false;
    private float backgroundSpeed;

    public TransitionToLevel(String nameBackground, int levelIndex, float backgroundSpeed) {
        this.nameBackground = nameBackground;
        this.levelIndex = levelIndex;
        this.backgroundSpeed = backgroundSpeed;
    }




    @Override
    public void assembleLevel() {
        var bb = Background.getInstance();
        bb.setSpeedTweened(backgroundSpeed);
    }

    @Override
    public void startLevel() {
        UI.getInstance().startLevel(levelIndex);
        EngineGetter.Instance().get().waitForFrames(()->ready = true,180);
        Background.getInstance().setBG(nameBackground);
        PlayerShip.getInstance().referenced().transform().setPosition(new Vector2(0,-200));
        PlayerShip.getInstance().getArsenal().setActive(true);
    }

    @Override
    public boolean completed() {
        return ready;
    }
}

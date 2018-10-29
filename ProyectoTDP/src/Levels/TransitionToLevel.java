package Levels;

import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.Ships.PlayerShip;
import RenderingSystem.Background;
import RenderingSystem.Sun;
import UI.UI;

public class TransitionToLevel extends AbstractLevel {

    private final boolean sun;
    private String nameBackground;
    private int levelIndex;
    private boolean ready = false;
    private float backgroundSpeed;

    public TransitionToLevel(String nameBackground, int levelIndex, float backgroundSpeed, boolean sun) {
        this.nameBackground = nameBackground;
        this.levelIndex = levelIndex;
        this.backgroundSpeed = backgroundSpeed;
        this.sun = sun;
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
        Sun.getInstance().setDaytime(sun);
    }

    @Override
    public boolean completed() {
        return ready;
    }
}

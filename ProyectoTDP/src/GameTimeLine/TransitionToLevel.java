package GameTimeLine;

import ADTs.Vector2;
import Engine.EngineGetter;
import Entities.Ships.Player.PlayerShip;
import RenderingSystem.Background;
import RenderingSystem.Backgrounds;
import RenderingSystem.Sun;
import UI.UI;

public class TransitionToLevel extends TimePoint {

    private final boolean sun;
    private Backgrounds nameBackground;
    private int levelIndex;
    private boolean ready = false;
    private float backgroundSpeed;

    public TransitionToLevel(Backgrounds nameBackground, int levelIndex, float backgroundSpeed, boolean sun) {
        this.nameBackground = nameBackground;
        this.levelIndex = levelIndex;
        this.backgroundSpeed = backgroundSpeed;
        this.sun = sun;
    }




    @Override
    public void assembleMoment() {
        var bb = Background.getInstance();
        bb.setSpeedTweened(backgroundSpeed);
    }

    @Override
    public void startMoment() {
        UI.getInstance().startLevel(levelIndex);
        EngineGetter.Instance().get().waitForFrames(()->ready = true,180);
        Background.getInstance().setBG(nameBackground);
        PlayerShip.getInstance().getArsenal().setActive(true);
        Sun.getInstance().setDaytime(sun);
    }

    @Override
    public boolean completed() {
        return ready;
    }
}

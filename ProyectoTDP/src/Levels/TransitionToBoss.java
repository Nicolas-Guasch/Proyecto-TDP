package Levels;

import Audio.SoundManager;
import Engine.EngineGetter;
import Entities.Ships.PlayerShip;
import IAs.PlayerMove;
import IAs.Slippery;
import InputManager.DirectionalMouse;
import InputManager.DirectionalWASD;
import SoundSystem.Sound;

public class TransitionToBoss extends AbstractLevel {

    private boolean complete = false;


    @Override
    public void assembleLevel() {

    }



    @Override
    public void startLevel() {
        EngineGetter.Instance().get().waitForFrames(this::StopMusic,10);
        EngineGetter.Instance().get().waitForFrames(this::ChangeControls,90);
        EngineGetter.Instance().get().waitForFrames(this::MusicStuff,180);
        EngineGetter.Instance().get().waitForFrames(this::EnableContinue,270);
    }

    private void StopMusic() {
        SoundManager.Instance().ImperialMarchStop();
        SoundManager.Instance().VaderBreath();
    }

    private void ChangeControls(){
        var player = PlayerShip.getInstance();
        var handler = player.getPilot().getHandler();
        DirectionalMouse direction = new DirectionalMouse(player.referenced().transform());
        DirectionalWASD move = new DirectionalWASD();
        handler = new PlayerMove(handler,move,direction);
        handler = new Slippery(handler);
        player.getPilot().setHandler(handler);
    }
    private void MusicStuff(){
        SoundManager.Instance().MusicBoss();
    }
    private void EnableContinue(){
        complete = true;
    }

    @Override
    public boolean completed() {
        return complete;
    }
}
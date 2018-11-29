package GameTimeLine;

import Audio.SoundManager;

import Engine.TheEngine;
import Engine.While;
import Entities.Ships.Player.PlayerShip;
import IAs.EntityQuery;
import IAs.PlayerMove;
import IAs.Slippery;
import InputManager.DirectionalMouse;
import InputManager.DirectionalWASD;
import RenderingSystem.Background;

public class TransitionToBoss extends TimePoint {

    private boolean complete = false;

    private static boolean perfectSpeed() {
        return Background.getInstance().getSpeedBackground() > 0.01f;
    }

    private static void changeSpeed() {
        float v = Background.getInstance().getSpeedBackground();
        v *= 0.99f;
        Background.getInstance().setSpeedBackground(v);
    }


    @Override
    public void assembleMoment() {
        makeBarricades();
    }

    private void makeBarricades() {

    }


    @Override
    public void startMoment() {
        TheEngine.getInstance().waitForFrames(this::StopMusic,10);
        TheEngine.getInstance().waitForFrames(this::ChangeControls,90);
        TheEngine.getInstance().waitForFrames(this::MusicStuff,180);
        TheEngine.getInstance().waitForFrames(this::EnableContinue,270);
        new While(TransitionToBoss::perfectSpeed, TransitionToBoss::changeSpeed).Excecute();

    }

    private void StopMusic() {
        SoundManager.Instance().ImperialMarchStop();
        SoundManager.Instance().VaderBreath();
    }

    private void ChangeControls(){
        PlayerShip player = PlayerShip.getInstance();
        EntityQuery handler = player.getPilot().getHandler();
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

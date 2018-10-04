package GameData;



import Engine.Components.Transform;
import Engine.Vector2;
import SoundSystem.*;
import Audio.AudioManager;
import Tools.Random;

import java.util.Collection;
import java.util.LinkedList;

public final class SoundManager
{

    private static final float MaxDist = 1000; //MaximaDistanciaAudicion



    private Sound pew;
    private Sound pew2;
    private Sound tie;
    private Music imperialMarch;
    private Music menu;
    private Sound vaderBreath;
    private Transform listenPoint;

    private Collection<Sound> Sounds;
    private Collection<Music> Musics;

    private boolean paused;

    private SoundManager()
    {

        Sounds = new LinkedList<>();
        Musics = new LinkedList<>();

        TinySound.init();
        pew  = TinySound.loadSound(AudioManager.Pew);
        pew2  = TinySound.loadSound(AudioManager.Pew2);
        tie  = TinySound.loadSound(AudioManager.TieAttack);
        vaderBreath  = TinySound.loadSound(AudioManager.VaderBreath);
        imperialMarch  = TinySound.loadMusic(AudioManager.ImperialMarch);
        menu  = TinySound.loadMusic(AudioManager.Menu);
        imperialMarch.setVolume(0.2f);
        menu.setVolume(0.3f);

        menu.pause();


        Sounds.add(pew);
        Sounds.add(pew2);
        Sounds.add(tie);
        Sounds.add(vaderBreath);
        Musics.add(imperialMarch);


    }

    private static SoundManager instance;
    public static SoundManager Instance(){
        if(instance==null)
        {
            instance = new SoundManager();
        }
        return instance;
    }

    public void Pew()
    {
        pew.play(0.05f);
    }
    public void ImperialMarchPlay()
    {
        imperialMarch.play(true);
    }
    public void ImperialMarchStop()
    {
        imperialMarch.stop();
    }

    public void SoloShoot()
    {
        //TODO : add other sound
        Pew();
    }

    public void TieDowns(Vector2 source)
    {
        PlayAt(source,tie);
    }

    //plays Pew at this position
    public void Pew(Vector2 position)
    {

        PlayAt(position, Random.value()>.5f?pew:pew2);
    }


    private void PlayAt(Vector2 vec, Sound sound)
    {
        Vector2 point = listenPoint.position();
        Vector2 top = listenPoint.top();
        // -------- VOL --------------
        float vol = (MaxDist - (vec.distanceTo(point)));
        if(vol>10)
        {
            vol = vol/MaxDist;
        }
        else{
            vol =0;
        }
        vol *= 0.5f;
        // ------------- Pan -------------

        float pan = 1;
        Vector2 go_to = vec.minus(point);
        pan = go_to.getUnaryAngle(top)*2;

        sound.play(vol,pan);
    }

    public void VaderBreath()
    {
        vaderBreath.play();
    }

    public void setTransformListener(Transform transform)
    {
        this.listenPoint = transform;
    }

    public void TieEngine(Vector2 position)
    {
        PlayAt(position,tie);
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if(paused)
        {
            Musics.forEach(Music::pause);
            menu.resume();
        }
        else
        {
            Musics.forEach(Music::resume);
            menu.pause();
        }
    }

    public boolean getPaused() {
        return paused;
    }

    public void gameOver() {
        //TODO: cargar cancion de game over
    }

    public void YouWin() {

    }
}

package Audio;
import java.util.*;


import Engine.Components.Transform;
import ADTs.Vector2;
import SoundSystem.*;

import java.util.Collection;
import java.util.LinkedList;

public final class SoundManager
{

    private static final float MaxDist = 1000; //MaximaDistanciaAudicion

    private Sound radio;
    private Music quote;

    private Sound pew;
    private Sound pew2;
    private Sound tie;
    private Music musicGame;
    private Music musicBoss;
    private Music menu;
    private Sound vaderBreath;
    private Transform listenPoint;

    private Sound explo1,explo2;

    private Collection<Sound> Sounds;
    private Collection<Music> Musics;

    private boolean paused;

    private Music currentTrack;

    private SoundManager()
    {

        Sounds = new LinkedList<>();
        Musics = new LinkedList<>();

        TinySound.init();
        pew  = TinySound.loadSound(AudioManager.Pew);
        pew2  = TinySound.loadSound(AudioManager.Pew2);
        tie  = TinySound.loadSound(AudioManager.TieAttack);
        vaderBreath  = TinySound.loadSound(AudioManager.VaderBreath);

        menu = TinySound.loadMusic(AudioManager.MusicGame); //si, los swapee
        musicGame  = TinySound.loadMusic(AudioManager.Menu);
        radio = TinySound.loadSound(AudioManager.SOLORADIO);
        quote = TinySound.loadMusic(AudioManager.SOLOQUOTE);
        musicGame.setVolume(0.6f);
        menu.setVolume(0.2f);
        menu.setLoop(true);
        musicBoss = TinySound.loadMusic(AudioManager.MusicBoss);

        explo1 = TinySound.loadSound(AudioManager.EXPLO1);
        explo2 = TinySound.loadSound(AudioManager.EXPLO2);

        menu.pause();
        currentTrack = musicGame;


        Sounds.add(pew);
        Sounds.add(pew2);
        Sounds.add(tie);
        Sounds.add(explo1);
        Sounds.add(explo2);
        Sounds.add(radio);

        Sounds.add(vaderBreath);
        Musics.add(musicGame);
        Musics.add(musicBoss);
        Musics.add(quote);


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

    public void explo(Vector2 position){
        Sound s = new Random().nextBoolean() ? explo1:explo2;
        PlayAt(position,s);
    }

    public void ImperialMarchPlay()
    {
        musicGame.play(true);

    }
    public void ImperialMarchStop()
    {
        musicGame.stop();
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
        PlayAt(position, Tools.Random.value()>.5f?pew:pew2);
    }

    public void Quote(){
        quote.play(false,0.6f);
    }
    public void Radio(){
        radio.play(0.7f);
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
        Vector2 go_to = vec.sub(point);
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
            currentTrack.resume();
            if(!quote.done())quote.resume();
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

    public void MusicBoss() {
        currentTrack = musicBoss;
        musicBoss.play(true);
    }
    public void MusicBossStop() {
        musicBoss.stop();
    }
}

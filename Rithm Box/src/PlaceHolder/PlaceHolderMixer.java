package PlaceHolder;

import SoundSystem.Sound;
import SoundSystem.TinySound;

public class PlaceHolderMixer
{

    private int bpm = 120;

    private Sound kick;
    private Sound hihat;
    private Sound clap;
    private Sound bass1;
    private Sound bass2;

    private Thread kicks;
    private Thread hihats;
    private Thread claps;
    private Thread bassline1;
    private Thread bassline2;

    public PlaceHolderMixer()
    {
        var chanel_kicks  = new Bar(bpm,new boolean[]{true,false,false,false,});
        var chanel_hihats = new Bar(bpm,new boolean[]{false,false,true,false,});
        var chanel_claps  = new Bar(bpm,new boolean[]{false,false,false,false,true,false,false,false,});
        var chanel_bass1  = new Bar(bpm,new boolean[]{false,false,false,true,false,false,false,false,});
        var chanel_bass2  = new Bar(bpm,new boolean[]{false,false,false,false,false,false,true,false,});
        chanel_kicks.suscribe(this::kickssound);
        chanel_hihats.suscribe(this::hihatssound);
        chanel_claps.suscribe(this::clapssound);
        chanel_bass1.suscribe(this::bassline1sound);
        chanel_bass2.suscribe(this::bassline2sound);
        kicks = new Thread(chanel_kicks);
        hihats = new Thread(chanel_hihats);
        claps = new Thread(chanel_claps);
        bassline1 = new Thread(chanel_bass1);
        bassline2 = new Thread(chanel_bass2);

        TinySound.init();

        kick = TinySound.loadSound(Sounds.kicks);
        hihat = TinySound.loadSound(Sounds.hihats);
        clap = TinySound.loadSound(Sounds.claps);
        bass1 = TinySound.loadSound(Sounds.bassline1);
        bass2 = TinySound.loadSound(Sounds.bassline2);
    }

    private void bassline2sound(Boolean aBoolean) {

    }

    private void bassline1sound(Boolean aBoolean) {


    }

    private void clapssound(Boolean aBoolean) {

    }

    private void hihatssound(Boolean aBoolean) {

    }

    private void kickssound(Boolean aBoolean) {
    }




}

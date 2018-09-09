package ParaTestear;



import SoundSystem.*;

public class SoundManager
{

    private Sound pew;
    private Music imperialMarch;

    private SoundManager()
    {
        TinySound.init();
        pew  = TinySound.loadSound(Paths.Pew);
        imperialMarch  = TinySound.loadMusic(Paths.ImperialMarch);
        imperialMarch.setVolume(0.2f);
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

}

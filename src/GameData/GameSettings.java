package GameData;

public class GameSettings
{
    // Singleton
    private static GameSettings instance;
    public static GameSettings Instance()
    {
        if(instance==null)
        {
            instance = new GameSettings();
        }
        return instance;
    }
    private GameSettings(){}

    //--------------------------------

    // ------ Configurations -----
    public final short FPS = 60;








}

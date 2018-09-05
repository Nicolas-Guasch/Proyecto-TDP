package GameData;

public class GameSettings
{
    // Singleton
    private static GameSettings instance;
    public static GameSettings GetInstance()
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
    public final short FPS = 10;









}

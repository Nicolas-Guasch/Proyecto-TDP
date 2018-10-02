package Levels;

public class LevelDirector
{

    private static LevelDirector instance;


    public static LevelDirector getInstance()
    {
        if(instance==null){
            instance = new LevelDirector();
        }
        return instance;
    }
    private LevelDirector(){}

    public Level currenteLevel;


}

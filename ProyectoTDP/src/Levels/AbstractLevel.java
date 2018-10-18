package Levels;

public abstract class AbstractLevel
{
    protected AbstractLevel nextLevel;







    public abstract void assembleLevel();
    public abstract void startLevel();
    public abstract boolean completed();

    public void clean(){}
}

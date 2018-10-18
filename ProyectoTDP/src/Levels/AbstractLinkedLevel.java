package Levels;

public abstract class AbstractLinkedLevel
{
    protected AbstractLinkedLevel nextLevel;

    public AbstractLinkedLevel nextLevel() {
        return nextLevel;
    }

    public void setNextLevel(AbstractLinkedLevel level){
        nextLevel = level;
    }

    public boolean hasNext() {
        return nextLevel !=null;
    }

    public abstract void assembleLevel();
    public abstract void startLevel();
    public abstract boolean completed();
}

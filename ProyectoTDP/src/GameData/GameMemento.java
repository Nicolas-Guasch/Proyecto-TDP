package GameData;

import Levels.AbstractLevel;
import Mementos.IMementoPlayer;

public class GameMemento
{
    private AbstractLevel level;
    private IMementoPlayer playerStatus;

    public GameMemento(AbstractLevel level, IMementoPlayer playerStatus) {
        this.level = level;
        this.playerStatus = playerStatus;
    }

    public IMementoPlayer getPlayerStatus() {
        return playerStatus;
    }

    public AbstractLevel getLevel() {
        return level;
    }
}

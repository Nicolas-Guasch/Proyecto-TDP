package GameData;

import Levels.TimePoint;
import Mementos.IMementoPlayer;

public class GameMemento
{
    private TimePoint level;
    private IMementoPlayer playerStatus;

    public GameMemento(TimePoint level, IMementoPlayer playerStatus) {
        this.level = level;
        this.playerStatus = playerStatus;
    }

    public IMementoPlayer getPlayerStatus() {
        return playerStatus;
    }

    public TimePoint getLevel() {
        return level;
    }
}

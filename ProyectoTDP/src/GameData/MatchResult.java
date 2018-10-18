
package GameData;

public class MatchResult implements ILostOrWin{
    private static MatchResult instance;

    public static MatchResult getInstance() {
        if (instance == null) {
            instance = new MatchResult();
        }
        return instance;
    }

    private ILostOrWin derivated;

    private MatchResult() {
        derivated = new LostOrWin();
    }

    @Override
    public void AllianceWins() {
        derivated.AllianceWins();
    }

    @Override
    public void EmpireWins() {
        derivated.EmpireWins();
    }
}


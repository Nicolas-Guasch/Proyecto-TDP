package GameTimeLine;

import Misc.DeathStar;

public class RemoveDeathStar extends TimePoint {
    @Override
    public void assembleMoment() {
    }

    @Override
    public void startMoment() {
        DeathStar.get().destroy();
    }

    @Override
    public boolean completed() {
        return true;
    }
}

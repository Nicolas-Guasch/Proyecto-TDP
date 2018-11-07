package GameTimeLine;

import Misc.DeathStar;

public class PutDeathStar extends TimePoint {

    @Override
    public void assembleMoment() {
        DeathStar.get();
    }

    @Override
    public void startMoment() {

    }

    @Override
    public boolean completed() {
        return true;
    }
}

package SpecialPowers;

import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.Pusher;

public class TheForcePower implements ISpecialPower
{
    @Override
    public void aply() {
        var pos = PlayerShip.getInstance().referenced().transform().position();
        EveryOne.getInstance().takeLazyVisitor(new Pusher(650,pos));
    }
}

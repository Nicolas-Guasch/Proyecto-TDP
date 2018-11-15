package SpecialPowers;

import ADTs.Vector2;
import Entities.EveryOne;
import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.Pusher;

public class TheForcePower implements ISpecialPower
{
    @Override
    public void aply() {
        Vector2 pos = PlayerShip.getInstance().referenced().transform().position();
        EveryOne.getInstance().takeLazyVisitor(new Pusher(650,pos));
    }
}

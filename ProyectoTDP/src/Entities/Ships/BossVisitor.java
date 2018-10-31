package Entities.Ships;

import Entities.Ships.Player.PlayerShip;
import EntitiesVisitor.VisitorEntity;

public class BossVisitor extends VisitorEntity {

    private final ShipBoss ship;

    public BossVisitor(ShipBoss shipBoss) {
        this.ship = shipBoss;
    }

    @Override
    public void visit(PlayerShip playerShip) {
        playerShip.data().setShield(0);
        playerShip.data().setHealth(0);
    }
}

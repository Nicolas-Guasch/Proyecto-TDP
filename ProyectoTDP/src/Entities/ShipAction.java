package Entities;

import ADTs.Vector2;
import Entities.Ships.BaseEnemyShip;
import Entities.Ships.Ship;

import java.util.function.Consumer;

public class ShipAction implements Consumer<Vector2> {
    private final Consumer<Ship> action;
    private final Ship ship;

    public ShipAction(Ship ship, Consumer<Ship> shipConsumer) {
        this.ship = ship;
        this.action = shipConsumer;
    }

    @Override
    public void accept(Vector2 vector2) {
        action.accept(ship);
    }
}

package Entities.Ships;

import Engine.GameObject;
import Entities.Weapons.Arsenal;

public abstract class BaseEnemyShip extends Ship{
    protected BaseEnemyShip(GameObject referenced, Arsenal weapons) {
        super(referenced, weapons);
    }
    public Arsenal getBagPack() {
        return weapons;
    }
}

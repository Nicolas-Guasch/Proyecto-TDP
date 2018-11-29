package Entities.Ships;

import Engine.IGameObject;
import Entities.Weapons.Arsenal;

public abstract class BaseEnemyShip extends Ship{
    protected BaseEnemyShip(IGameObject referenced, Arsenal weapons) {
        super(referenced, weapons);
    }
    public Arsenal getBagPack() {
        return weapons;
    }
}

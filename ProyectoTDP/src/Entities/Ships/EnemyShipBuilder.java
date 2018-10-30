package Entities.Ships;

import Engine.GameObject;
import Entities.Ships.EnemyShip;

public abstract class EnemyShipBuilder
{
    protected BaseEnemyShip ship;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        ship = new EnemyShip(go);
    }
    public BaseEnemyShip get()
    {
        return ship;
    }
    public abstract void assembleSprite();
    public abstract void assembleHitBox();
    public abstract void assembleWeapons();
    public abstract void assembleBehaviours();
    public abstract void assembleData();

}

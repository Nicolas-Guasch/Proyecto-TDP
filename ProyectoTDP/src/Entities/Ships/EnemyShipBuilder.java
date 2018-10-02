package Entities.Ships;

import Engine.GameObject;
import Entities.Ships.EnemyShip;

public abstract class EnemyShipBuilder
{
    protected EnemyShip ship;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        ship = new EnemyShip(go);
    }
    public EnemyShip get()
    {
        return ship;
    }
    public abstract void assembleSprite();
    public abstract void assembleHitBox();
    public abstract void assembleWeapons();
    public abstract void assembleBehaviours();
    public abstract void assembleData();

}

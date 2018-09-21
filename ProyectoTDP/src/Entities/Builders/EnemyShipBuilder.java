package Entities.Builders;

import Engine.GameObject;
import Entities.EnemyShip;

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
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}

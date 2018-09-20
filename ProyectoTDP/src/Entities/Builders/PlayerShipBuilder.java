package Entities.Builders;

import Engine.GameObject;
import Entities.PlayerShip;

public abstract class PlayerShipBuilder
{
    protected PlayerShip ship;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        PlayerShip.initialize(go);
        ship = PlayerShip.getInstance();
    }
    public PlayerShip get()
    {
        return ship;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}
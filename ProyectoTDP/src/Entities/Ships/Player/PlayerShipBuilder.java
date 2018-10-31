package Entities.Ships.Player;

import Engine.GameObject;

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
    public abstract void assembleWeapons();
    public abstract void assembleHitBox();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}

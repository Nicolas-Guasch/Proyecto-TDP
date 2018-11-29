package Entities.Builders;

import Engine.GameObject;
import Engine.IGameObject;
import Entities.BarricadeEnem;

public abstract class BarricadeEnemBuilder
{
    protected BarricadeEnem obst;

    public void create()
    {
        IGameObject go = GameObject.getRoot().addChild();
        obst = new BarricadeEnem(go);
    }
    public BarricadeEnem get()
    {
        return obst;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}

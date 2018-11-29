package Entities.Builders;

import Engine.GameObject;
import Engine.IGameObject;
import Entities.BarricadeBoth;

public abstract class BarricadeBothBuilder
{
    protected BarricadeBoth obst;

    public void create()
    {
        IGameObject go = GameObject.getRoot().addChild();
        obst = new BarricadeBoth(go);
    }
    public BarricadeBoth get()
    {
        return obst;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}


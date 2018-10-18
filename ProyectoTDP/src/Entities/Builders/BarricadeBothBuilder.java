package Entities.Builders;

import Engine.GameObject;
import Entities.BarricadeBoth;

public abstract class BarricadeBothBuilder
{
    protected BarricadeBoth obst;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
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


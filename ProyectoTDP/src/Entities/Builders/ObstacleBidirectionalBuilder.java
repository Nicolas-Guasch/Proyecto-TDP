package Entities.Builders;

import Engine.GameObject;
import Entities.ObstacleBidirectional;

public abstract class ObstacleBidirectionalBuilder
{
    protected ObstacleBidirectional obst;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        obst = new ObstacleBidirectional(go);
    }
    public ObstacleBidirectional get()
    {
        return obst;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}


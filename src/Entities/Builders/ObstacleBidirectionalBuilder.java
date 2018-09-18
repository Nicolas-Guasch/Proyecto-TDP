package Entities.Builders;

import Engine.GameObject;
import Entities.ObstacleBidirectional;

public abstract class ObstacleBidirectionalBuilder
{
    protected ObstacleBidirectional bullet;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        bullet = new ObstacleBidirectional(go);
    }
    public ObstacleBidirectional get()
    {
        return bullet;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}


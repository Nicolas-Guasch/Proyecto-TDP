package Entities.Builders;

import Engine.GameObject;
import Entities.ObstacleMonoDirectional;

public abstract class ObstacleMonoDirectionalBuilder
{
    protected ObstacleMonoDirectional bullet;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        bullet = new ObstacleMonoDirectional(go);
    }
    public ObstacleMonoDirectional get()
    {
        return bullet;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}

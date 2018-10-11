package Entities.Builders;

import Engine.GameObject;
import Entities.ObstacleMonoDirectional;

public abstract class ObstacleMonoDirectionalBuilder
{
    protected ObstacleMonoDirectional obst;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        obst = new ObstacleMonoDirectional(go);
    }
    public ObstacleMonoDirectional get()
    {
        return obst;
    }
    public abstract void assembleSprite();
    public abstract void assembleCollider();
    public abstract void assembleBehaviours();
    public abstract void assembleData();
}

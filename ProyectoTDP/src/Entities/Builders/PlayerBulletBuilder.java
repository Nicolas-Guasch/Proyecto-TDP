package Entities.Builders;

import Engine.GameObject;
import Engine.IGameObject;
import Entities.PlayerBullet;

public abstract class PlayerBulletBuilder implements IBulletBuilder<PlayerBullet>
{
    protected PlayerBullet bullet;

    public void create()
    {
        IGameObject go = GameObject.getRoot().addChild();
        bullet = new PlayerBullet(go);
    }
    public PlayerBullet get()
    {
        return bullet;
    }
}


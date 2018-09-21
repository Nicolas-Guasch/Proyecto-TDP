package Entities.Builders;

import Engine.GameObject;
import Entities.PlayerBullet;

public abstract class PlayerBulletBuilder implements IBulletBuilder<PlayerBullet>
{
    protected PlayerBullet bullet;

    public void create()
    {
        var go = GameObject.getRoot().addChild();
        bullet = new PlayerBullet(go);
    }
    public PlayerBullet get()
    {
        return bullet;
    }
}


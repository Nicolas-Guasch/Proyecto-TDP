package Entities.Builders;

import Engine.GameObject;
import Engine.IGameObject;
import Entities.EnemyBullet;

public abstract class EnemyBulletBuilder implements IBulletBuilder<EnemyBullet>
{
    protected EnemyBullet bullet;

    public void create()
    {
        IGameObject go = GameObject.getRoot().addChild();
        bullet = new EnemyBullet(go);
    }
    public EnemyBullet get()
    {
        return bullet;
    }
}


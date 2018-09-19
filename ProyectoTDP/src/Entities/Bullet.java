package Entities;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.IBullet;


/**
 * Models a bullet
 */
public abstract class Bullet extends Entity implements IBullet
{
    protected Bullet(GameObject referenced) {
        super(referenced);
    }
    @Override
    public Transform transform() {
        return getReferenced().getTransform();
    }

    @Override
    public Entity entity() {
        return this;
    }


}

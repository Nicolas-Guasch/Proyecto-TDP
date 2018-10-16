package Entities;

import Engine.Components.Transform;
import Engine.GameObject;
import Entities.Builders.IBullet;
import EntitiesVisitor.VisitorEntitie;

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
        return referenced().transform();
    }

    @Override
    public Entity entity() {
        return this;
    }

}

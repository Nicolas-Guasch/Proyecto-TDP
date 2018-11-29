package Entities;

import Engine.Components.Transform;
import Engine.IGameObject;
import Entities.Builders.IBullet;
import GameData.GameSettings;

/**
 * Models a bullet
 */
public abstract class Bullet extends Entity implements IBullet
{
    protected Bullet(IGameObject referenced) {
        super(referenced);
        data = GameSettings.GetInstance().PlaceHolderData();
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

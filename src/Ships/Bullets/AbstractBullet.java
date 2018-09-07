package Ships.Bullets;

import Engine.Component;
import Engine.Vector2;
import Ships.Puppets.AbstractShipPuppet;

public abstract class AbstractBullet<BulletType extends AbstractBullet<BulletType>> extends Component
{
    protected BulletData data;

    public AbstractBullet(BulletData data) {
        this.data=data;
    }

    public abstract void BeShooted(Vector2 Direction);
    public abstract void Destroy();


    public final BulletData getData()
    {
        return data;
    }
    public final void SetData(BulletData data)
    {
        this.data=data;
    }

    public abstract BulletType clone();

}


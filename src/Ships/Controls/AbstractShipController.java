package Ships.Controls;

import Ships.Bullets.AbstractBullet;
import Ships.Entities.Ship;
import Ships.Puppets.AbstractShipPuppet;

public abstract class AbstractShipController<ShipEntity extends Ship,BulletType extends AbstractBullet, PuppetType extends AbstractShipPuppet<BulletType>>
{

    protected BulletType prototype;
    protected PuppetType puppet;
    protected ShipEntity entity;

    public AbstractShipController(PuppetType puppet, BulletType prototype)
    {

    }




    public final BulletType getPrototype() {
        return prototype;
    }

    public final void setPrototype(BulletType prototype) {
        this.prototype = prototype;
    }
}

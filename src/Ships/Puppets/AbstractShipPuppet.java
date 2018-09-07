package Ships.Puppets;

import Engine.Component;
import Engine.Vector2;
import Ships.Bullets.*;

public abstract class AbstractShipPuppet<BulletType extends AbstractBullet> extends Component
{

    public abstract void Shoot(Vector2 direction,BulletType bullet);
    public abstract void Move(Vector2 direction);
    public abstract void Faint();


}

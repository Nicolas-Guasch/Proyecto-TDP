package Ships.Puppets;

import Engine.Components.Transform;
import Engine.GameObject;
import Engine.Vector2;
import Ships.Bullets.SoloBullet;

public class SoloShipPuppet extends AbstractShipPuppet<SoloBullet>
{
    @Override
    public void Shoot(Vector2 direction, SoloBullet bulletComponent)
    {
        GameObject bullet = gameObject().addChild();
        SoloBullet b = bullet.addComponent(bulletComponent);
        b.BeShooted(direction);
    }

    @Override
    public void Move(Vector2 direction)
    {
        transform().MoveTowards(direction);
    }

    @Override
    public void Faint() {
//TODO: implementar explocion
    }

}

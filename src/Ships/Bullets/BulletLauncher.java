package Ships.Bullets;

import BufferSystem.Buffer;
import Engine.Components.Transform;
import Engine.Vector2;

public class BulletLauncher extends AbstractBulletLauncher
{
    private Transform shootPoint;

    public BulletLauncher(Bullet prototype, Transform shootpoint)
    {
        super(prototype);
        this.shootPoint = shootpoint;
    }
    public Bullet get()
    {
        Bullet b = buffer.get();
        b.getReferenced().SetEnabled(true);
        b.getReferenced().getTransform().setPosition(shootPoint.getPosition());
        b.getReferenced().getTransform().setTop(shootPoint.getTop());
        return b;
    }

}

package Ships.Bullets;

import BufferSystem.Buffer;

public abstract class AbstractBulletLauncher
{
    protected Buffer<Bullet> buffer;
    protected Bullet prototype;

    AbstractBulletLauncher(Bullet prototype)
    {
        buffer = new Buffer<>(20, 200, prototype);
        this.prototype = prototype;
    }
    public abstract Bullet get();

    public void changePrototype(Bullet prototype)
    {
        buffer.clearBuffer();
        buffer = new Buffer<>(20, 200, prototype);
        this.prototype = prototype;
    }

    public void Recycle(Bullet b)
    {
        b.getReferenced().SetEnabled(false);

        buffer.Recycle(b);
    }

    public void CleanLauncher()
    {
        buffer.clearBuffer().forEach(x -> x.getReferenced().Destroy());
    }

}

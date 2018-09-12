package Entities;

import Engine.EngineFactory;
import Tools.Giver;

public abstract class Weapon
{
    private Giver<Bullet> giver;
    protected int bulletLifeFrames;

    public Weapon(Giver<Bullet> giver, int bulletLifeFrames)
    {
        this.giver = giver;
    }

    public void Shoot()
    {
        Bullet b = giver.get();
        b.referenced().addComponent(new EnsureOneCollition());
        b.referenced().getRenderer().Show();
        EngineFactory.Instance().get().WaitForFrames(()->{
            b.referenced().Destroy();
        },bulletLifeFrames);
        DoShoot(b);
    }
    protected abstract void DoShoot(Bullet bullet);
}

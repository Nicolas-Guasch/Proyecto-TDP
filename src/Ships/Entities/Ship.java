package Ships.Entities;


import BufferSystem.Buffer;
import Engine.GameObject;
import Ships.Bullets.AbstractBulletLauncher;
import Ships.Bullets.Bullet;
import Ships.Bullets.BulletLauncher;

public class Ship
{
    private GameObject Referenced;
    private AbstractBulletLauncher launcher;
    private AbstractShooter Shooter;


    public Ship()
    {

    }

    private void OnShoot(Bullet b)
    {

    }


    public GameObject getReferenced() {
        return Referenced;
    }

    public void setReferenced(GameObject referenced) {
        Referenced = referenced;
    }

    public AbstractBulletLauncher getLauncher() {
        return launcher;
    }

    public void setLauncher(AbstractBulletLauncher launcher) {
        this.launcher = launcher;
    }

    public AbstractShooter getShooter() {
        return Shooter;
    }

    /*
    public void setShooter(AbstractShooter shooter) {
        Shooter = shooter;
        shooter.getOnShoot().Suscribe(this::OnShoot);
    }*/
}

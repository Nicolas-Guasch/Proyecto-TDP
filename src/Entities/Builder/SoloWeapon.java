package Entities.Builder;

import Engine.Component;
import Engine.Components.Transform;
import Engine.Vector2;
import Entities.Bullet;
import Entities.Ship;
import Entities.Weapon;
import ParaTestear.SoundManager;
import SoundSystem.Sound;
import Tools.Giver;
import UtilsBehaviours.Directioned;

public class SoloWeapon extends Weapon
{
    private static final int soloBulletsFrames = 600;//10 seconds with 60 fps
    private final Vector2 phaseShift;
    private final Ship solo;
    private static final float SoloWeaponForce = 10;


    public SoloWeapon(Giver<Bullet> giver, Ship solo, Vector2 phaseShift) {
        super(giver,soloBulletsFrames);
        this.solo = solo;
        this.phaseShift = phaseShift;
    }

    int i=0;
    @Override
    protected void DoShoot(Bullet bullet)
    {
        Transform str = solo.getReferenced().getTransform();
        Vector2 outPos = str.position().sum(str.getTop(phaseShift.y())).sum(str.getTop().right(phaseShift.x()));
        Component c1 = new Directioned(solo.getReferenced().getTransform().getTop(SoloWeaponForce));
        bullet.referenced().addComponent(c1);
        bullet.referenced().getTransform().setPosition(outPos);
        bullet.referenced().getTransform().setTop(str.getTop());
    }
}

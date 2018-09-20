package Entities;

import Engine.GameObject;
import Entities.Weapons.Weapon;

public class WeaponReward extends Reward
{

    private Weapon weapon;


    public WeaponReward(GameObject referenced)
    {
        super(referenced);
    }

    @Override
    public void collideWith(PlayerShip ent)
    {
        ent.addWeapon(weapon);
        Destroy();
    }

    @Override
    public void collideWith(EnemyShip ent) {
        ent.addWeapon(weapon);
        Destroy();
    }


}

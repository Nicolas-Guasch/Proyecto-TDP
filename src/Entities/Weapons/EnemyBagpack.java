package Entities.Weapons;

import java.util.ArrayList;

public class EnemyBagpack extends WeaponSet
{

    protected ArrayList<Weapon> weapons;
    private int index = 0;

    public EnemyBagpack()
    {
        weapons = new ArrayList<>();
    }


    public void switchCurrent()
    {
        if(isActive())
        {
            index++;
            index = (index < weapons.size()) ? index : 0;
        }
    }

    @Override
    public void add(Weapon weapon) {
        weapons.add(weapon);
    }

    public void shoot()
    {
        if(isActive() && !weapons.isEmpty())
        {
            weapons.get(index).Shoot();
        }
    }

    public boolean isEmpty()
    {
        return weapons.isEmpty();
    }

    @Override
    public void remove(Weapon weapon) {
        weapons.remove(weapon);
    }

    public Weapon getCurrent()
    {
        if(weapons.isEmpty())
            throw new EmptyWeaponsBagpackException("The Bagpack is empty, use isEmpty");
        return weapons.get(index);
    }
}

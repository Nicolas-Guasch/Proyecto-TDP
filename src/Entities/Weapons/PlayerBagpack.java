package Entities.Weapons;

import InputManager.AbstractDiscreteInput;

import java.util.ArrayList;

public class PlayerBagpack extends WeaponSet
{
    protected ArrayList<Weapon> weapons;
    private AbstractDiscreteInput Switch;
    private AbstractDiscreteInput Shoot;

    private int index = 0;
    public PlayerBagpack(AbstractDiscreteInput switchKey, AbstractDiscreteInput shootKey)
    {
        weapons = new ArrayList<>();
        Switch = switchKey;
        Shoot = shootKey;
        shootKey.OnAction().Suscribe(this::_shoot);
        switchKey.OnAction().Suscribe(this::_switch);
    }


    private void _shoot(boolean isPressed)
    {
        if(isPressed && isActive())
        {
            shoot();
        }
    }

    private void _switch(boolean isPressed)
    {
        if(isPressed && isActive())
        {
            switchCurrent();
        }
    }

    public void switchCurrent()
    {
        index++;
        index = (index < weapons.size()) ? index : 0;
    }

    @Override
    public void add(Weapon weapon) {
        weapons.add(weapon);
    }

    public void shoot()
    {
        if(!weapons.isEmpty())
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

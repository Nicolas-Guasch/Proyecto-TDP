package Entities.Weapons;

import Observer.IBroadcaster;
import Observer.Invoker;
import Observer.observerPack;
import Observer.ObserverSystem;
import InputManager.AbstractDiscreteInput;

import java.util.ArrayList;

public class PlayerArsenal extends Arsenal
{
    private final IBroadcaster<Boolean> broadcaster;
    private final Invoker<Boolean> invoker;
    protected ArrayList<Weapon> weapons;
    private AbstractDiscreteInput Switch;
    private AbstractDiscreteInput Shoot;

    private int index = 0;
    public PlayerArsenal(AbstractDiscreteInput switchKey, AbstractDiscreteInput shootKey)
    {
        weapons = new ArrayList<>();
        Switch = switchKey;
        Shoot = shootKey;
        shootKey.OnAction().suscribe(this::_shoot);
        switchKey.OnAction().suscribe(this::_switch);
        observerPack<Boolean> pack = ObserverSystem.getInstance().getBroadcaster();
        broadcaster = pack.Broadcaster;
        invoker = pack.Invoker;
    }


    private void _shoot(boolean isPressed)
    {
        if(isPressed && isActive())
        {
            shoot();
            invoker.invoke(true);
        }
    }
    @Override
    public Arsenal clone() {
        var n = new PlayerArsenal(Switch,Shoot);
        weapons.forEach(n::add);
        return n;
    }

    private void _switch(boolean isPressed)
    {
        if(isPressed && isActive())
        {
            switchCurrent();
            invoker.invoke(true);
        }
    }

    public void switchCurrent()
    {

        index++;
        index = (index < weapons.size()) ? index : 0;
        if(!weapons.isEmpty())
        {
            Weapon w = weapons.get(index);
            if(w.isEmpty())
            {
                weapons.remove(w);
                switchCurrent();
            }
            invoker.invoke(true);
        }
    }

    @Override
    public void add(Weapon weapon) {
        weapons.add(weapon);
        invoker.invoke(true);
    }

    public void shoot()
    {
        if(!weapons.isEmpty())
        {
            if(weapons.get(index).isEmpty())
            {
                switchCurrent();
            }
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
        invoker.invoke(true);
    }

    public Weapon getCurrent()
    {
        if(weapons.isEmpty())
            throw new EmptyWeaponsBagpackException("The Bagpack is empty, use isEmpty");
        return weapons.get(index);
    }

    @Override
    public void destroy() {
        setActive(false);
        weapons.clear();
        invoker.invoke(true);
    }

    @Override
    public IBroadcaster<Boolean> observer() {
        return broadcaster;
    }
    @Override
    public Iterable<Weapon> weapons() {
        return weapons;
    }

}

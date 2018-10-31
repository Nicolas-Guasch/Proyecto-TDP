package Entities.Weapons;

import Broadcaster.IBroadcaster;
import Broadcaster.Invoker;
import Broadcaster.ObserverPack;
import Broadcaster.ObserverSystem;

import java.util.ArrayList;

public class EnemyArsenal extends Arsenal
{

    protected ArrayList<Weapon> weapons;
    private int index = 0;
    private IBroadcaster<Boolean> broadcaster;
    private Invoker<Boolean> invoker;

    public EnemyArsenal()
    {
        weapons = new ArrayList<>();
        ObserverPack<Boolean> pack = ObserverSystem.getInstance().GetBroadcaster();
        broadcaster = pack.Broadcaster;
        invoker = pack.Invoker;
    }


    public void switchCurrent()
    {
        if(isActive())
        {
            index++;
            index = (index < weapons.size()) ? index : 0;
            invoker.Invoke(true);
        }
    }

    @Override
    public void add(Weapon weapon)
    {
        weapons.add(weapon);
        invoker.Invoke(true);
    }

    @Override
    public Arsenal clone() {
        var n = new EnemyArsenal();
        weapons.forEach(n::add);
        return n;
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
    public void remove(Weapon weapon)
    {
        weapons.remove(weapon);
        invoker.Invoke(true);
    }

    public Weapon getCurrent()
    {
        if(weapons.isEmpty())
            throw new EmptyWeaponsBagpackException("The Bagpack is empty, use isEmpty");
        return weapons.get(index);
    }

    @Override
    public void destroy() {
        weapons.clear();
        setActive(false);
        invoker.Invoke(true);
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

package Entities.Behaviours;

import Engine.Component;
import Entities.Weapons.WeaponSet;
import Tools.Random;

/**
 * implements the behavior of a ship, their "fire frequency", which,
 * given a set of weapons, each 'freq' frames gives the
 * signal to the set of weapons to fire the selected weapon
 * and change to the next available weapon
 */

public class FireFrequency extends Component
{
    private int freq;
    private int i;
    private WeaponSet weapons;
    public FireFrequency(int freq, WeaponSet weapons)
    {
        this.freq=freq+ Random.value(0,freq/4);
        i=freq;
        this.weapons = weapons;
    }

    @Override
    public void update()
    {
        if(i>=freq)
        {
            if(!weapons.isEmpty()) {
                weapons.shoot();
                weapons.switchCurrent();
            }
            i=0;
        }
        i++;
    }

    @Override
    public void OnDestroy() {
        weapons = null;
    }
}

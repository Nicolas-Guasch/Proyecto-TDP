package Entities.Behaviours;

import Engine.Component;
import Entities.Weapons.WeaponSet;
import Tools.Random;

public class FreqShoot extends Component
{

    private int freq;
    private int i=0;

    private boolean left;
    private WeaponSet bagpack;
    public FreqShoot(int freq, WeaponSet bagpack)
    {
        this.freq=freq+ Random.value(0,freq/2);
        left = true;
        i=freq;
        this.bagpack = bagpack;
    }

    @Override
    public void Update()
    {
        if(i>=freq)
        {
            if(!bagpack.isEmpty()) {
                bagpack.shoot();
            }
            bagpack.switchCurrent();
            i=0;
        }
        i++;
    }
}

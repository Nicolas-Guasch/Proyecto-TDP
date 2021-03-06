package AIs;

import Engine.Component;
import Entities.Weapons.Arsenal;
import Tools.Random;


/**
 * implements the behavior of a ship, their "fire frequency", which,
 * given a set of weapons, each 'frequency' frames gives the
 * signal to the set of weapons to fire the selected weapon
 * and change to the next available weapon
 */
public class FireFrequency extends Component
{
    private final int shootFrequency;
    private Arsenal weapons;
    private int contadorDeFrames;
    public FireFrequency(int shootFrequency, Arsenal arsenal)
    {
        this.shootFrequency = shootFrequency + Math.abs(Random.value(0, shootFrequency /15)) - 3;
        this.weapons = arsenal;

        contadorDeFrames = shootFrequency;
    }

    @Override
    public void update()
    {
        if(contadorDeFrames >= shootFrequency)
        {
            if(!weapons.isEmpty()) {
                weapons.shoot();
                weapons.switchCurrent();
            }
            contadorDeFrames =0;
        }
        contadorDeFrames++;
    }

    @Override
    public void onDestroy() {
        weapons = null;
    }
}

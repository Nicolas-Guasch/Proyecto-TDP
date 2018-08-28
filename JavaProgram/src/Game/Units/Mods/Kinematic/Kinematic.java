package Game.Units.Mods.Kinematic;

import Game.Units.Mod;

public abstract class Kinematic extends Mod {

    protected int speed;

    public Kinematic(int x){
        speed=x;
    }

    public void setSpeed(int x){
        speed=x;
    }

    public int getSpeed(){
        return speed;
    }

    public abstract void move();
}

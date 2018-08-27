package Game.Units.Mods.Kinematic;

public abstract class Kinematic{

    protected int speed;
    protected int auxSpeed;
    protected boolean altered;

    public Kinematic(int x){
        speed=x;
        auxSpeed=x;
        altered=false;
    }

    public void setSpeed(int x){
        speed=x;
        auxSpeed=x;
        altered=false;
    }

    public int getSpeed(){
        return speed;
    }

    public boolean isAltered(){
        return altered;
    }

    public abstract void move();
}

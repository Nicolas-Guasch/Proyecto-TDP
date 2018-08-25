package Game.Characters.Kinematic;

public abstract class Kinematic{

    protected int speed;
    protected int auxSpeed;
    protected boolean altered;

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

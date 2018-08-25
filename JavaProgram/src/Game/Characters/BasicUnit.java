package Game.Characters;

import Game.Characters.Kinematic.Kinematic;
import Game.Characters.Life.Life;

public abstract class BasicUnit{

    protected Life life;
    protected Kinematic kinematic;
    protected Shooter shooter;

    public BasicUnit(Life f,Kinematic k, Shooter s){
        life=f;
        kinematic=k;
        shooter=s;
    }

    public int getLife(){
        return life.getLife();
    }

    public void setLife(int x){
        life.setLife(x);
    }

    public void takeDamage(int x){
        life.takeDamage(x);
    }

    public void setSpeed(int x){
        kinematic.setSpeed(x);
    }

    public int getSpeed(){
        return kinematic.getSpeed();
    }

    public void move(){
        kinematic.move();
    }

    public void setImpact(int x){
        shooter.setImpact(x);
    }

    public int getImpact(){
        return shooter.getImpact();
    }

    public abstract void accept(Collision c); // c.visit(this)



}

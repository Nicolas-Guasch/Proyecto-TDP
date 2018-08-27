package Game.Units;

import Game.Units.Mods.Fighter.Fighter;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;

public abstract class BasicUnit{

    protected Life life;
    protected Kinematic kinematic;
    protected Fighter fighter;
    protected Collision collision;

    public BasicUnit(Life l,Kinematic k, Fighter f){
        life=l;
        kinematic=k;
        fighter =f;
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
        fighter.setImpact(x);
    }

    public int getImpact(){
        return fighter.getImpact();
    }

    public abstract void accept(Collision c); // c.visit(this)




}

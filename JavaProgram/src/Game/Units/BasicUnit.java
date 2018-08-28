package Game.Units;

import Game.Units.Mods.Fighter.Fighter;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Life.Life;

import java.util.concurrent.Flow;


//TODO: hacer iupdatable
public abstract class BasicUnit implements Flow.Subscriber{

    protected Life life;
    protected Kinematic kinematic;
    protected Fighter fighter;
    protected OnCollide onCollide;
    protected int points;
    protected Flow.Subscription subscription;

    public BasicUnit(Life l,Kinematic k, Fighter f){
        life=l;
        kinematic=k;
        fighter =f;
        points=0;
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

    public abstract void accept(OnCollide c); // c.visit(this)

    public Fighter getModFighter(){
        return fighter;
    }

    public Life getModLife(){
        return life;
    }

    public Kinematic getModeKinematic(){
        return kinematic;
    }

    public void changeMod(Fighter f){
        fighter=f;
    }

    public void changeMod(Life l){
        life=l;
    }

    public void changeMod(Kinematic k){
        kinematic=k;
    }

    public int getPoints(){
        return points;
    }


    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onNext(Object o) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

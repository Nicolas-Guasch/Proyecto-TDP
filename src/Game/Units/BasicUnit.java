package Game.Units;

import Engine.MonoBehaviour;

import Game.Units.Mods.Fighter.Fighter;
import Game.Units.Mods.Fighter.FighterNone;
import Game.Units.Mods.Fighter.Weapon;
import Game.Units.Mods.Kinematic.Kinematic;
import Game.Units.Mods.Kinematic.KinematicNone;
import Game.Units.Mods.Life.Life;

public abstract class BasicUnit extends MonoBehaviour<BasicUnit>{

    protected UnitData data;
    protected Life life;
    protected Kinematic kinematic;
    protected Fighter fighter;
    protected OnCollide onCollide;
    protected int points;

    public BasicUnit(){

    }

    public BasicUnit(Life l,Kinematic k, Fighter f){
        life=l;
        kinematic=k;
        fighter =f;
        points=0;
    }

    public BasicUnit(Life l,Fighter f){
        life=l;
        fighter=f;
        kinematic=new KinematicNone();
    }

    public BasicUnit(Life l, Kinematic k){
        life=l;
        kinematic=k;
        fighter=new FighterNone();
    }

    public int getLife() {
        return life.getLife(data);
    }

    public void setLife(int l) {
        life.setLife(data,l);
    }

    public void setImpact(int x) {
        fighter.setImpact(data,x);
    }

    public int getImpact() {
        return fighter.getImpact(data);
    }


    public void loseWeapon() {
        fighter.loseWeapon(data);
    }


    public void loadWeapon(Weapon w) {
        fighter.loadWeapon(data,w);
    }


    public void setSpeed(int x) {
        kinematic.setSpeed(data,x);
    }


    public int getSpeed() {
        return kinematic.getSpeed(data);
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

    public void setData(UnitData d){
        data=d;
    }

}


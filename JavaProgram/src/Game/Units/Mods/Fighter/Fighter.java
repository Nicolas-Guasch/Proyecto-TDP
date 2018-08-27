package Game.Units.Mods.Fighter;

public abstract class Fighter {

    protected int impact;
    protected Weapon weapon;

    public Fighter(int i, Weapon w){
        impact=i;
        weapon=w;
    }

    public void setImpact(int x){
        impact=x;
    }

    public int getImpact(){
        return impact;
    }

    public abstract void attack();

}

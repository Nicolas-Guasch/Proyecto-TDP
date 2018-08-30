package Game.Units.Mods.Life;

public class LifeNoDamage extends Life {

    public LifeNoDamage(int l) {
        super(l);
    }

    @Override
    public void takeDamage(int punch) {
        super.takeDamage(0);
    }

    @Override
    public void setLife(int x) {
        //Bloquea el set
    }
}

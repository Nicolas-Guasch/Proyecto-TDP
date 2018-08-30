package Game.Units.Effects;

import Game.Units.BasicUnit;
import Game.Units.Mod;

public abstract class Effect {

    protected int time;
    protected Mod savedMod;
    protected BasicUnit unit;

    public void Effect(int t){
        time=t;
    }

    public abstract void apply(BasicUnit u); //Debe salvar unidad y mod

    public abstract void restore(); //Restaura el mod a la unidad y libera ambas variables.


}

package Game.Units.Mods.Life;

import Game.Units.Mod;

public class Life extends Mod {

    protected int life;

    public Life(int l){
        life=l;
    }

    public int getLife(){
        return life;
    }

    public void setLife(int x){
        life=x;
    }

    public void takeDamage(int punch){
        life= life>punch? life-punch:0;
        //Observer: Notificar muerte. Imprimir certificado de defunción. Rip in peace maquinola.
    }
}

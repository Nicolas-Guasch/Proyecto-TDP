package Game.Characters.Life;

public class Life {

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

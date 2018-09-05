package Game.Units.Mods.Life;

import Game.Units.Mod;
import Game.Units.UnitData;

public abstract class Life extends Mod {

    protected int criticLimit=20;

    public int getLife(UnitData data){
        return data.getLife();
    }

    public void setLife(UnitData data,int x){
        data.setLife(x);
    }

    public void addLife(UnitData data,int x){
        data.addLife(x);
    }

    public void takeDamage(UnitData data, int punch){
        if(punch!=0){
            int life = data.getLife();
            life= life>punch? life-punch:0;
            data.setLife(life);
            calculateCritic(data);
        }
        //Observer: Notificar muerte. Imprimir certificado de defunción. Rip in peace maquinola.
    }

    protected void calculateCritic(UnitData data){
        if((data.getLife()/data.getMaxLife())*100<criticLimit){
            inCritic(data);
        }
    }

    protected abstract void inCritic(UnitData data);





}

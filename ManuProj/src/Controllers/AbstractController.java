package Controllers;

import GUI.IUpdatable;
import GameObjects.Ship;
import GameObjects.Vector2;
import GameObjects.Vector2Int;
import Map.Map;

import javax.swing.*;

public abstract class AbstractController implements IUpdatable {

    protected Ship controlled;
    protected boolean isFiring;
    protected Icon r,l,d,u,lu,ru, rd, ld,c;
    protected Icon[][] m;




    private Icon getIcon(Vector2Int v){
        return m[v.getX()+1][v.getY()+1];
        //obtiene el icono asociado de la matriz en funcion del vector
    }



    protected void move(Vector2 vec)
    {
        controlled.setDirec(vec);
        controlled.setSprite(getIcon(Vector2Int.Implicit(vec.norma())));

    }



    protected abstract void checkShoot();

    protected abstract Vector2 armarVector();

    public boolean isFiring() {
        return isFiring;
    }

    public void Fire() {
        controlled.fire();
    }

    public void endFire() {
        controlled.stopFiring();

    }

}

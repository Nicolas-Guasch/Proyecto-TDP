package Controllers;

import Assets.Paths;
import GUI.listenerTemp;
import GameObjects.Enemy;
import GameObjects.Vector2;
import Map.Map;

import javax.swing.*;

public class EnemyController extends AbstractController {
    Behaviour b;


    public EnemyController(Enemy e, Behaviour be){
        b = be;
        r = new ImageIcon(Paths.ENEMY1);
        l = new ImageIcon(Paths.ENEMY1);
        d = new ImageIcon(Paths.ENEMY1);
        u = new ImageIcon(Paths.ENEMY1);
        lu = new ImageIcon(Paths.ENEMY1);
        ru = new ImageIcon(Paths.ENEMY1);
        rd = new ImageIcon(Paths.ENEMY1);
        ld = new ImageIcon(Paths.ENEMY1);
        c = new ImageIcon(Paths.ENEMY1);
        controlled = e;

        m = new ImageIcon[3][3];
        m[0][0]=lu;
        m[1][0]=u;
        m[2][0]=ru;
        m[0][1]=l;
        m[1][1]=c;
        m[2][1]=r;
        m[0][2]=rd;
        m[1][2]=d;
        m[2][2]=ld;
    }

    @Override
    protected void checkShoot()
    {

        if(b.fire())
        {
            Fire();
        }
        else
        {
            endFire();
        }
    }

    @Override
    protected Vector2 armarVector() {

        return b.getDir();
    }

    @Override
    public void update(Map map)
    {
        if (controlled.isAlive()) {
            if(listenerTemp.getInstance().kill)
                controlled.die();
            map.onUpdate(this);
            Vector2 vec = armarVector();
            move(vec);
            checkShoot();
        }
        else
            destroyMe(map);
    }

    @Override
    public void destroyMe(Map map) {
        map.destroy(this);
    }


}

package Rewards;

import ADTs.Vector2;
import Engine.Component;
import java.util.*;
public class RewardMove extends Component
{

    private int minX,maxX;
    private float counter= 1;
    private Vector2 speed = Vector2.LEFT();
    public RewardMove(){
        var r = new Random();
        minX = Math.abs(r.nextInt(200))+200;
        minX *= -1;
        maxX = Math.abs(r.nextInt(200))+200;
        assert maxX>=200;
        assert maxX<=400;
        assert minX<=-200;
        assert minX>=-400;
        assert maxX > minX;
    }


    @Override
    public void update() {
        // -------- Controles -------- (todos tienen exitpoint)
        if(transform()==null) return;

        if(transform().position().x()<minX){
            counter=1;
            speed = Vector2.RIGHT();
        }
        if(transform().position().x()>maxX){
            counter=1;
            speed = Vector2.LEFT();
        }
        //----------- movimientos ---------

        counter += Math.abs(new Random().nextInt(20))/10f;
        speed = speed.withLength(counter);

        transform().moveTowards(speed);
    }
}

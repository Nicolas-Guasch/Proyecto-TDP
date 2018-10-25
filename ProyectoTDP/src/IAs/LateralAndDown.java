package IAs;

import ADTs.Vector2;
import Entities.Entity;
import java.util.*;
public class LateralAndDown extends AIQueryDecorator
{
    private int maxDown = 90, maxLateral = 90;
    private int down, lateral;

    private IDirGiver dirGiver, lat, dwn;


    public LateralAndDown(EntityQuery decorated) {
        super(decorated);
        down = 0;
        lateral = 0;
        lat = Tools.Random.value(20, 40) > 30 ? new GetAndRotate(Tools.Random.value(1, 10) * 0.001f, Vector2.LEFT(), true) : new GetAndRotate(Tools.Random.value(1, 10) * 0.001f, Vector2.RIGHT(), true);
        dwn = new GetAndRotate(0.005f,Vector2.DOWN(),false);
        if(Tools.Random.value(20,900)>333)
        dirGiver = lat;
        else
        dirGiver = dwn;
    }

    @Override
    public Vector2 whereToMove(Entity ent) {
        if(down>maxDown|| Math.abs(new Random().nextInt(90))<2){
            down = 0;
            dirGiver = lat;
        }
        if(lateral>maxLateral || Math.abs(new Random().nextInt(90))<2){
            lateral = 0;
            dirGiver = dwn;
        }
        if(dirGiver == dwn){
            down ++;
        }
        else{
            lateral++;
        }
        return dirGiver.get();
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        var v = dirGiver.get().rotateUnary(0.5f);
        if(v.y()>0){
            v = v.prod(-1);
        }
        return v;
    }
}

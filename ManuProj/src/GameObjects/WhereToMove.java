package GameObjects;

import Assets.Configs;
import Assets.Paths;
import Map.Map;

import javax.swing.*;


public class WhereToMove
{


    private Vector2 ubication;

    private static WhereToMove instance;
    private static WhereToMove getInstance(){
        if(instance == null){
            instance = new WhereToMove();
        }
        return instance;
    }


    private WhereToMove()
    {

    }
    long t = 0;

    private float ymax = 200;
    private float yc   = 125;
    private float ymin = 50;
    private float xmax = 950;
    private float xc   = 500;
    private float xmin = 50;
    private float xl = 275;
    private float xr = 725;



    private Vector2 whereToMove(Vector2 ubication) {
        this.ubication = ubication;
        t++;
        Vector2 vec = new Vector2(1,1);
        float x = ubication.getX() ;
        float y = ubication.getY() ;

        if(inH(x,y) || inC(x,y))
        {
            return new Vector2(1,1);
        }
        if(inB(x,y) || inA(x,y))
        {
            return new Vector2(1,-1);
        }
        if(inG(x,y) || inD(x,y))
        {
            return new Vector2(-1,1);
        }
        if(inF(x,y) || inE(x,y))
        {
            return new Vector2(-1,-1);
        }
        return vec;

    }

    private boolean inA(float x , float y)
    {
        return x>xl && x<xc && y>yc && y<ymax;
    }
    private boolean inB(float x , float y)
    {
        return x>xc && x<xr && y>ymin && y<yc;
    }
    private boolean inC(float x , float y)
    {
        return x>xr && x<xmax && y>ymin && y<yc;
    }
    private boolean inD(float x , float y)
    {
        return x>xr && x<xmax && y>yc && y<ymax;
    }
    private boolean inE(float x , float y)
    {
        return x>xc && x<xr && y>yc && y<ymax;
    }

    private boolean inF(float x , float y)
    {
        return x>xl && x<xc && y>ymin && y<yc;
    }
    private boolean inG(float x , float y)
    {
        return x>xmin && x<xl && y>ymin && y<yc;
    }
    private boolean inH(float x , float y)
    {
        return x>xmin && x<xl && y>yc && y<ymax;
    }

}

package Controllers;

import GameObjects.Vector2;


public class EnemyBehaviour extends Behaviour {




    private float ampx = 0.13f;
    private float ampy = 0.07f;


    private float radioGral = 0.26f;


    public EnemyBehaviour(){
        direc = Vector2.LEFT();
    }



    public Vector2 getDir(){
        updateDir();
        return direc;
    }

    @Override
    public boolean fire() {
        double r = Math.random();
        if (r<0.01 ) return true;
        else return false;
    }


    private void updateDir(){
        float t = System.nanoTime()/1_000_000_000f;




        float x = (float) ((ampx * Math.sqrt(2)*(-Math.sin(t)*((Math.sin(t) * Math.sin(t)) + 1) - Math.sin(2*t) * Math.cos(t)))/
                         (((Math.sin(t) * Math.sin(t)) + 1) * ((Math.sin(t) * Math.sin(t)) + 1)));
                //(float) ((Math.cos(t) * ampx * Math.sqrt(2))/((Math.sin(t) * Math.sin(t)) + 1)) ;


        float y =  (float) ( (ampy * (3 * Math.cos(2 * t) - 1) ) /
                ((( Math.sqrt(2) * (( Math.sin(t) * Math.sin(t)) + 1 )*((Math.sin(t) * Math.sin(t) ) + 1 ))))) ;
                //(float) ((Math.sin(t) * Math.cos(t) * ampx * Math.sqrt(2))/((Math.sin(t) * Math.sin(t)) + 1));



                direc = (new Vector2(x,y));
        direc = direc.prod(radioGral).prod(1);


    }



}

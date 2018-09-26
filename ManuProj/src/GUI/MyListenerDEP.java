package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyListenerDEP implements KeyListener {


    private static MyListenerDEP instance;
    public static MyListenerDEP Instance()
    {
        if(instance==null)
            instance = new MyListenerDEP();
        return  instance;
    }


    public boolean up,down,left,right,fire;//TODO: encapsular

    private MyListenerDEP()
    {
        right=false;
        down=false;
        up=false;
        left=false;
        fire = false;
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_CONTROL){
            fire=true;
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            right=true;
            System.out.println("r");
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
                left = true;
                System.out.println("l");

        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
                up = true;
            System.out.println("u");
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
                down = true;
            System.out.println("d");
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

            right=false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){

            left = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_UP){
            up = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            down = false;
        }


        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_CONTROL){
            fire = false;
        }
    }


    @Override public void keyTyped(KeyEvent e) {}
}


package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class listenerTemp implements KeyListener{

private static  listenerTemp instance;
        public boolean kill;


        public static listenerTemp getInstance(){
            if (instance == null)
                instance = new listenerTemp();
            return instance;
        }
        private listenerTemp()
        {
           kill = false;
        }

        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode() == KeyEvent.VK_ALT) {

                kill=true;
            }
        }
        public void keyReleased(KeyEvent e) {


        }

         public void keyTyped(KeyEvent e) {}
    }



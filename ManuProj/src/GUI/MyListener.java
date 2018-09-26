package GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyListener implements KeyListener {


    private static MyListener instance;
    public static MyListener Instance()
    {
        if(instance==null)
            instance = new MyListener();
        return  instance;
    }

    //TODO: a los llamados poneles () y listo, se llaman iguales

    //private final AtomicBoolean a;

    private final AtomicBoolean up = new AtomicBoolean(false);
    private final AtomicBoolean down = new AtomicBoolean(false);
    private final AtomicBoolean left = new AtomicBoolean(false);
    private final AtomicBoolean right = new AtomicBoolean(false);
    private final AtomicBoolean fire = new AtomicBoolean(false);
    
    public synchronized boolean up()
    {
    	return up.get();
    }
    public synchronized boolean down()
    {
    	return down.get();
    }
    public synchronized boolean left()
    {
    	return left.get();
    }
    public synchronized boolean right()
    {
    	return right.get();
    }
    public synchronized boolean fire()
    {
    	return fire.get();
    }




    private MyListener()
    {
      /*  right=false;
        down=false;
        up=false;
        left=false;
        fire = false;*/
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_CONTROL){
            synchronized(fire)
            {
            	fire.lazySet(true);
            }            
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            synchronized(right)
            {
            	right.lazySet(true);
            }            
            //System.out.println("r");
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
			synchronized(left)
			{
				left.lazySet(true);
			}
			//System.out.println("l");
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            synchronized(up)
            { 
            up.lazySet(true);
        	}
           // System.out.println("u");
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            synchronized(down)
            { 
			down.lazySet(true);
			}
            //System.out.println("d");
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_CONTROL){
            synchronized(fire)
            {
				fire.lazySet(false);            	
            }            
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            synchronized(right)
            {
				right.lazySet(false);            	
            }            
            //System.out.println("r --");
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
			synchronized(left)
			{
				left.lazySet(false);				
			}
			//System.out.println("l --");
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            synchronized(up)
            { 
				up.lazySet(false);            
        	}
           // System.out.println("u --");
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            synchronized(down)
            { 
				down.lazySet(false);			
			}
            //System.out.println("d --");
        }
    }


    @Override public void keyTyped(KeyEvent e) {}
}


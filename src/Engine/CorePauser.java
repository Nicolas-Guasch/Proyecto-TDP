package Engine;

import GameData.SoundManager;
import RenderingSystem.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.JOptionPane.MESSAGE_PROPERTY;
import static javax.swing.JOptionPane.showMessageDialog;

public class CorePauser implements KeyListener
{
    private static CorePauser instance;


    private Core core;
    private boolean paused = false;
    private CorePauser(){
        core = Core.getInstance();
    }
    public static CorePauser Instance()
    {
        if (instance==null)
        {
            instance = new CorePauser();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            paused = !paused;
            core.setPaused(paused);
            SoundManager.Instance().setPaused(paused);
            if(paused)
            {
                Window.GetInstance().ShowPause();
            }
            else
            {
                Window.GetInstance().HidePause();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

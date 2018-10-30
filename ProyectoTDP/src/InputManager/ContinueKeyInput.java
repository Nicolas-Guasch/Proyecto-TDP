package InputManager;


import RenderingSystem.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.HashSet;

public class ContinueKeyInput extends AbstractContinueInput
{
    private KeyListener Listener;
    private Collection<Character> mychars;
    private boolean happens;

    public ContinueKeyInput(String chars)
    {
        mychars = new HashSet<>();
        for (char c:chars.toCharArray())
        {
            mychars.add(c);
        }
        happens = false;
        Listener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(mychars.contains(e.getKeyChar()))
                {
                    happens = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(mychars.contains(e.getKeyChar()))
                {
                    happens = false;
                }
            }
            public void keyTyped(KeyEvent e) {

            }
        };
        Window.GetInstance().AddInput(Listener);
    }
    public boolean happens()
    {
        if(!Window.GetInstance().InFocus()){
            happens=false;
        }

        return happens ;
    }
    public void Destroy()
    {
        Window.GetInstance().RemoveInput(Listener);
    }
}



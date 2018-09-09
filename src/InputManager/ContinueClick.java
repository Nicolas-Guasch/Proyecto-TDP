package InputManager;

import RenderingSystem.Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;



public class ContinueClick extends AbstractContinueInput
{
    private boolean happens;
    private MouseListener Listener;

    public ContinueClick()
    {
        initialize(0);
    }
    public ContinueClick(int mouseButton)
    {
        initialize(mouseButton);
    }

    private void initialize(int mouseButton)
    {
         happens = false;
         Listener = new MouseListener(){
            public void mousePressed(MouseEvent e)
            {
                if(e.getButton() == mouseButton)
                {
                    happens = true;
                }
            }
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == mouseButton)
                {
                    happens = false;
                }
            }
            public void mouseClicked(MouseEvent e){
                if(e.getButton() == mouseButton)
                {

                }
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        };
        Window.GetInstance().AddInput(Listener);
    }

    @Override
    public boolean Happens() {
        return happens;
    }

    @Override
    public void Destroy() {
        Window.GetInstance().RemoveInput(Listener);
    }
}

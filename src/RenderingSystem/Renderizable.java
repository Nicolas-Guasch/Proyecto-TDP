package RenderingSystem;

import Engine.Component;
import Engine.Vector2;
import GameData.GameSettings;

import javax.swing.*;
import java.awt.*;

public class Renderizable extends Component
{
    private JLabel label;
    private boolean visible = false;
    private static float h = GameSettings.GetInstance().sizeWindow.height;
    private static float w = GameSettings.GetInstance().sizeWindow.width;
    private int radius;
    private boolean rotable = true;
    public Renderizable(SpriteData data)
    {
        label = new JLabel();
        label.setIcon(data.icon());
        label.setBounds(0,0,data.getWidth(),data.getHeight());//TODO: cambiar esto

        label.setVisible(false);
        Window.GetInstance().AddJComponent(label);
        radius = 40;
    }

    public Renderizable(SpriteData data, int Radio)
    {
        this(data);
        this.radius = Radio;
    }

    public void Start()
    {
        transform().setPosition(Vector2.ORIGIN());
    }

    public void OnDestroy()
    {
        Window.GetInstance().Remove(label);
    }

    public void Show()
    {
        visible = true;
        label.setVisible(visible);
    }
    public void Update()
    {
        Volver();
        if(visible)
        {
            ChangePosition();
        }
    }

    private void Volver()
    {
        if(rotable)
        {
            int c = radius;
            Vector2 v = transform().getPosition();
            if(v.x()>(w/2)+2*c){
                transform().setPosition(new Vector2(-(w/2)-c,v.y()));
            }
            if(v.x()<-(w/2)-2*c){
                transform().setPosition(new Vector2((w/2)+c,v.y()));
            }
            if(v.y()>(h/2)+2*c){
                transform().setPosition(new Vector2(v.x(),-(h/2)-c));
            }
            if(v.y()<-(h/2)-2*c){
                transform().setPosition(new Vector2(v.x(),(h/2)+c));
            }
        }
    }

    private void ChangePosition()
    {

        Dimension d = (Lerp(transform().getPosition()));
        var x = d.width;
        var y = d.height;

        label.setBounds(x,y,label.getSize().width,label.getSize().height);

    }

    private Dimension Lerp(Vector2 vec)
    {
        return new Dimension((int)(vec.x()+w/2) ,(int)((h/2)-vec.y()));
    }

    public void setRotable(boolean rotable) {
        this.rotable = rotable;
    }
}

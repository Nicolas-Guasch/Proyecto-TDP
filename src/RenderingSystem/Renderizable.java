package RenderingSystem;

import Engine.Component;
import Engine.Components.Vector2;
import GameData.GameSettings;

import javax.swing.*;
import java.awt.*;

public class Renderizable extends Component
{
    private JLabel label;
    private boolean visible = false;
    private static float h = GameSettings.GetInstance().sizeWindow.height;
    private static float w = GameSettings.GetInstance().sizeWindow.width;


    public Renderizable(SpriteData data)
    {
        label = new JLabel();
        label.setIcon(data.icon());
        label.setBounds(0,0,data.getWidth(),data.getHeight());//TODO: cambiar esto

        label.setVisible(false);
        Window.GetInstance().AddLabel(label);
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
        int c = 30;
        Vector2 v = transform().getPosition();
        if(transform().getPosition().x()>(w/2)+2*c){
            transform().setPosition(new Vector2(-(w/2)-c,v.y()));
        }
        if(transform().getPosition().x()<-(w/2)-2*c){
            transform().setPosition(new Vector2((w/2)+c,v.y()));
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
}

package RenderingSystem;

import Engine.Component;
import Engine.Vector2;
import GameData.GameSettings;

import java.awt.*;

public class Renderizable extends Component
{
    private SpriteRenderer label;
    private boolean visible = false;
    private static float h = GameSettings.GetInstance().sizeWindow.height;
    private static float w = GameSettings.GetInstance().sizeWindow.width;

    public Renderizable(SpriteData data)
    {
        label = new SpriteRenderer();
        //label = new JLabel();
        label.setIcon(data.icon());
        label.setBounds(0,0,data.getWidth(),data.getHeight());//TODO: cambiar esto
        label.setVisible(false);
        Window.GetInstance().AddJComponent(label);
    }


    public void Start()
    {
        label.setTransform(transform());
        Window.GetInstance().SetZ(label,transform().getZcomponent(),true);
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
        //Volver();
        if(visible)
        {
            ChangePosition();
        }
    }

    public Vector2 getSize()
    {
        return new Vector2(label.getWidth(),label.getHeight());
    }


    private void ChangePosition()
    {
        Dimension d = RenderingTools.WorldToCanvas(transform().getPosition());
        var x = d.width;
        var y = d.height;

        label.setBounds(x-label.getWidth()/2,y-label.getHeight()/2,label.getSize().width,label.getSize().height);
    }



}

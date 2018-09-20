package RenderingSystem;

import Engine.Component;
import Engine.Vector2;
import GameData.GameSettings;

import java.awt.*;

public class Renderizable extends Component
{
    protected  SpriteRenderer label;
    protected  boolean visible = false;
    protected  static float h = GameSettings.GetInstance().sizeWindow.height;
    protected  static float w = GameSettings.GetInstance().sizeWindow.width;
    protected  SpriteData spriteData;

    public Renderizable(SpriteData data)
    {
        label = new SpriteRenderer();
        //label = new JLabel();

        label.setIcon(data.icon());
        spriteData = data;
        label.setBounds(0,0,data.getWidth(),data.getHeight());//TODO: cambiar esto
        label.setVisible(false);
        Window.GetInstance().AddJComponent(label);
    }

    //just for testing //TODO: borrar luego de testear colliders
    protected Renderizable()
    {
        label = new SpriteRenderer();
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
    public void Hide()
    {
        visible = false;
        label.setVisible(false);
    }

    public void Update()
    {
        //Volver();
        if(visible)
        {
            ChangePosition();
        }
    }

    @Override
    public void OnEnable() {
        Show();
    }

    @Override
    public void OnDisable() {
        Hide();
    }

    public Vector2 getSize()
    {
        return new Vector2(label.getWidth(),label.getHeight());
    }


    protected void ChangePosition()
    {
        Dimension d = RenderingTools.WorldToCanvas(transform().position());
        var x = d.width;
        var y = d.height;

        label.setBounds(x-label.getWidth()/2,y-label.getHeight()/2,label.getSize().width,label.getSize().height);
    }


    public boolean equals(Object o)
    {
        Renderizable other;
        try{
            other = (Renderizable)o;
        }
        catch (Exception c){return false;}
        return other.spriteData == spriteData;

    }
    public Renderizable clone()
    {
        return new Renderizable(spriteData);
    }

    public SpriteData getSpriteData() {
        return spriteData;
    }
}

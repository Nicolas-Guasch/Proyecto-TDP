package RenderingSystem;

import Engine.Component;
import ADTs.Vector2;
import GameData.GameSettings;

import java.awt.*;

public class Renderizable extends Component
{
    protected  SpriteRenderer label;
    protected  boolean visible = false;
    protected  static float h = GameSettings.GetInstance().sizeWindow.height;
    protected  static float w = GameSettings.GetInstance().sizeWindow.width;
    protected  SpriteData spriteData;
    private float zphaseshift=0;

    public Renderizable(SpriteData data)
    {
        label = new SpriteRenderer();
        label.setIcon(data.icon());
        spriteData = data;
        label.setBounds(0,0,data.getWidth(),data.getHeight());//TODO: cambiar esto
        label.setVisible(false);
        Window.GetInstance().AddJComponent(label);
    }

    public Renderizable(SpriteData data, float zphaseshift) {
        label = new SpriteRenderer();
        label.setIcon(data.icon());
        spriteData = data;
        label.setBounds(0,0,data.getWidth(),data.getHeight());//TODO: cambiar esto
        label.setVisible(false);
        Window.GetInstance().AddJComponent(label);
        this.zphaseshift = zphaseshift;
    }

    public void Start()
    {
        label.setTransform(transform());
        Window.GetInstance().SetZ(label,transform().getZcomponent()+zphaseshift,true);
        transform().setPosition(Vector2.ORIGIN());
    }

    public void OnDestroy()
    {
        Window.GetInstance().Remove(label);
    }

    public void show()
    {
        visible = true;
        if(gameObject()!=null)
        {
            Update();
        }
    }
    public void Hide()
    {
        visible = false;
        label.setVisible(false);
    }

    public void Update()
    {

        if(visible)
        {
            label.setVisible(!transform().position().near(Vector2.ORIGIN()));
            ChangePosition();
        }
        else
        {
            label.setVisible(false);
        }


    }

    @Override
    public void OnEnable() {
        show();
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

    public SpriteRenderer Sprite() {
        return label;
    }
}

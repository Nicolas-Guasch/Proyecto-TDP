package ParaTestear;

import Engine.Components.Transform;
import Engine.Vector2;
import RenderingSystem.*;
import RenderingSystem.Window;

import java.awt.*;

/**
 * solo para testear los colliders
 */
public class SonRenderer extends Renderizable {

    Transform toFollow;

    public void setSprite(SpriteRenderer re)
    {
        label = re;
    }

    public void Start()
    {
        Window.GetInstance().SetZ(label,transform().getZcomponent()-1,true);
        transform().setPosition(Vector2.ORIGIN());
    }

    public void setToFollow(Transform transform)
    {
        label.setTransform(transform);
        toFollow = transform;
    }

    @Override
    public void Update() {
        if(visible)
        {
            ChangePosition();
        }

    }
    protected void ChangePosition()
    {
        Dimension d = RenderingTools.WorldToCanvas(toFollow.position());
        var x = d.width;
        var y = d.height;

        label.setBounds(x-label.getWidth()/2,y-label.getHeight()/2,label.getSize().width,label.getSize().height);
    }
}

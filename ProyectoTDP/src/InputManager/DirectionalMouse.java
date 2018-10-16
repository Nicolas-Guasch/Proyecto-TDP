package InputManager;

import Engine.Components.Transform;
import ADTs.Vector2;
import RenderingSystem.RenderingTools;

import java.awt.*;

public class DirectionalMouse extends AbstractDirectionalInput
{
    private Transform reference;

    public DirectionalMouse(Transform reference)
    {
        this.reference = reference;
    }

    @Override
    public void Destroy()
    {
        reference = null;
    }

    public Vector2 Direction()
    {
        if(reference!=null)
        {
            int mouseX = MouseInfo.getPointerInfo().getLocation().x;
            int mouseY = MouseInfo.getPointerInfo().getLocation().y;
            Vector2 mousePos = RenderingTools.CanvasToWorld(new Dimension(mouseX,mouseY));
            return mousePos.minus(reference.position()).versor();
        }
        return Vector2.ORIGIN();
    }

}

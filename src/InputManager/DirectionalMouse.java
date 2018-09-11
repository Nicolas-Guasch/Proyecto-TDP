package InputManager;

import Engine.Components.Transform;
import Engine.Vector2;
import RenderingSystem.RenderingTools;

import java.awt.*;
import java.awt.event.MouseListener;

public class DirectionalMouse extends AbstractDirectionalInput
{
    private Transform reference;
    private MouseListener listener;
    public DirectionalMouse(Transform reference)
    {
        this.reference = reference;
    }

    @Override
    public void Destroy()
    {
        //dont need to destroy this
    }

    public Vector2 Direction()
    {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        Vector2 mousePos = RenderingTools.CanvasToWorld(new Dimension(mouseX,mouseY));
        return mousePos.minus(reference.getPosition()).versor();
    }

}

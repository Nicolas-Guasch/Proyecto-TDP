package RenderingSystem;

import Engine.Components.Transform;
import Engine.Vector2;

import javax.swing.*;
import java.awt.*;

public class SpriteRenderer extends JLabel
{
    private static final long serialVersionUID = 1L;
    private Transform transform;

    public SpriteRenderer()
    {

    }

    @Override
    public void paintComponent( Graphics g ) {
        Graphics2D gx = (Graphics2D) g;
        if(transform != null)
        {

            gx.rotate(transform.top().getAngle(Vector2.UP(2)) , (getWidth()/2f) , (getHeight()/2f));

        }
        super.paintComponent(gx);
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
package RenderingSystem;

import Engine.Components.Transform;
import ADTs.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ColorModel;

public class SpriteRenderer extends JLabel
{

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
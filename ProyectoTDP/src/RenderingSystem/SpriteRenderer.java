package RenderingSystem;

import Engine.Components.ITransform;
import ADTs.Vector2;

import javax.swing.*;
import java.awt.*;

public class SpriteRenderer extends JLabel
{

    private ITransform ITransform;


    @Override
    public void paintComponent( Graphics g ) {
        if(ITransform ==null || ITransform.getLifetime()<5){
            return;
        }
        Graphics2D gx = (Graphics2D) g;
        if(ITransform != null)
        {
            gx.rotate(ITransform.top().getAngle(Vector2.UP(2)) , (getWidth()/2f) , (getHeight()/2f));
        }
        super.paintComponent(gx);
    }

    public void setTransform(ITransform ITransform) {
        this.ITransform = ITransform;
    }
}
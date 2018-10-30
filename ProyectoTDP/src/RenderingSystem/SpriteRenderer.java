package RenderingSystem;

import ADTs.Vector2;
import Engine.Components.Transform;

import javax.swing.*;
import java.awt.*;

public class SpriteRenderer extends JPanel
{
    private Image image;
    private Transform transform;
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void setIcon(ImageIcon icon){
        image = icon.getImage();
    }

    @Override
    public void paintComponent( Graphics g ) {
        Graphics2D gx = (Graphics2D) g;
        if(transform != null)
        {
            gx.rotate(transform.top().getAngle(Vector2.UP(2)) , (getWidth()/2f) , (getHeight()/2f));
        }
        super.paintComponent(gx);
        gx.drawImage(image,0,0,null);
    }



}

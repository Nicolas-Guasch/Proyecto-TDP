package RenderingSystem;

import ADTs.Vector2;
import Engine.Components.Transform;
import GameData.GameSettings;

import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
public class SpriteRenderer extends JPanel
{
    private Image image;
    private ImageIcon icon;
    private Transform transform;
    private static boolean drunkMode = GameSettings.DrunkMode;
    private static int drunkIntensity = GameSettings.DrunkIntensity;

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void setIcon(ImageIcon icon){
        image = icon.getImage();
        this.icon = icon;
    }



    @Override
    public void paintComponent( Graphics g ) {

        if(transform.getLifetime()<3){
            return;
        }

        Graphics2D gx = (Graphics2D) g.create();
        if(transform != null)
        {
            gx.rotate(transform.top().getAngle(Vector2.UP(2)) , (getWidth()/2f) , (getHeight()/2f));
        }

        if (!drunkMode) {
            gx.drawImage(image,0,0,icon.getImageObserver());
        } else {
            gx.drawImage(image,
                    new java.util.Random().nextInt(drunkIntensity)-drunkIntensity/2,
                    new java.util.Random().nextInt(drunkIntensity)-drunkIntensity/2,
                    icon.getImageObserver());
            gx.drawImage(image,
                    new java.util.Random().nextInt(drunkIntensity)-drunkIntensity/2,
                    new java.util.Random().nextInt(drunkIntensity)-drunkIntensity/2,
                    icon.getImageObserver());
        }


    }




}

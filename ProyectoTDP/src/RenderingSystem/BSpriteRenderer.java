package RenderingSystem;

import ADTs.Vector2;
import Engine.Components.Transform;
import GameData.GameSettings;

import javax.swing.*;
import java.awt.*;
import javax.swing.JLabel;
public class BSpriteRenderer extends JPanel
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


    Vector2 last = new Vector2(1000,1000);

    @Override
    public void paintComponent( Graphics g ) {

        if(transform.position().distanceTo(last)>100){
            last = transform.position();
            return;
        }
        last = transform.position();
        if(transform.getLifetime()<5){
            return;
        }

        Graphics2D gx = (Graphics2D) g.create();
        if(transform != null)
        {
            gx.rotate(transform.top().getAngle(Vector2.UP(2)) , (getWidth()/2f) , (getHeight()/2f));
        }
        gx.drawImage(image,0,0,null);//icon.getImageObserver());




    }




}

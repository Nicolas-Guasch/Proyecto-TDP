package RenderingSystem;

import ADTs.IVector2;
import ADTs.Vector2;
import Engine.Components.ITransform;
import GameData.GameSettings;

import javax.swing.*;
import java.awt.*;

public class BSpriteRenderer extends JPanel
{
    private Image image;
    private ImageIcon icon;
    private ITransform ITransform;
    private static boolean drunkMode = GameSettings.DrunkMode;
    private static int drunkIntensity = GameSettings.DrunkIntensity;

    public void setTransform(ITransform ITransform) {
        this.ITransform = ITransform;
    }

    public void setIcon(ImageIcon icon){
        image = icon.getImage();
        this.icon = icon;
    }


    IVector2 last = Vector2.get(1000,1000);

    @Override
    public void paintComponent( Graphics g ) {

        if(ITransform.position().distanceTo(last)>100){
            last = ITransform.position();
            return;
        }
        last = ITransform.position();
        if(ITransform.getLifetime()<5){
            return;
        }

        Graphics2D gx = (Graphics2D) g.create();
        if(ITransform != null)
        {
            gx.rotate(ITransform.top().getAngle(Vector2.UP(2)) , (getWidth()/2f) , (getHeight()/2f));
        }
        gx.drawImage(image,0,0,null);//icon.getImageObserver());




    }




}

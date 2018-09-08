package RenderingSystem;

import Engine.Vector2;

import javax.swing.*;
import java.net.URL;

public class SpriteData
{
    private  Icon icon;
    private int width;
    private int height;
    public SpriteData(URL path, Vector2 size)
    {
        width = (int)size.x();
        height =(int) size.y();
        icon = new ImageIcon(path);
    }

    public Icon icon() {
        return icon;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}

package RenderingSystem;

import Assets.AssetStore;

import ADTs.Vector2;

import javax.swing.*;

public class SpriteData
{
    private  ImageIcon icon;
    private int width;
    private int height;
    private String name;
    public SpriteData(String name, Vector2 size)
    {
        width = (int)size.x();
        height =(int) size.y();
        icon = (ImageIcon) AssetStore.getIcon(name);
        this.name = name;
    }
    public SpriteData(String name)
    {
        icon = (ImageIcon) AssetStore.getIcon(name);
        width = icon.getIconWidth();
        height = icon.getIconHeight();
        this.name = name;
    }

    public ImageIcon icon() {
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

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
}

package RenderingSystem;

import ADTs.IVector2;
import ADTs.Vector2;

public interface IRenderizable
{
    void show();
    void hide();
    IVector2 getSize();
    SpriteRenderer Sprite();
    boolean isVisible();
}

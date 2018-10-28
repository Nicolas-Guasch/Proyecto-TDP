package RenderingSystem;

import ADTs.Vector2;

public interface IRenderizable
{
    void show();
    void hide();
    Vector2 getSize();
    SpriteRenderer Sprite();
    boolean isVisible();
}

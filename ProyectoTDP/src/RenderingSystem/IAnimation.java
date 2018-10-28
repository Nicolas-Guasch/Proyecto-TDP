package RenderingSystem;

public interface IAnimation
{
    void setSpeed(float speed);//1 to common speed
    void restart();
    int frames();
    void show();
    void hide();
    void setFrame(int frame);
    Renderizable getRenderizable();
}

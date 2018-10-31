package RenderingSystem;

import Assets.AssetStore;
import Engine.Component;

import java.util.ArrayList;
import java.util.List;


/** importante: guardar las animaciones con
 * los nombre anim_0.png, anim_1.png, anim_2.png
 * (empezar desde 0)
 */
public class Animation extends Component implements IAnimation {

    private static String separator = "_";
    private boolean oneShoot = false;

    private Renderizable renderizable;
    private List<SpriteData> sprites = new ArrayList<>();
    private SpriteRenderer renderer;

    private int index = 0;
    private int delay = 20; // cada 20 frames cambia de imagen

    public Animation(String icons, Renderizable rend, boolean oneShoot){
        this(icons,rend);
        this.oneShoot = oneShoot;
    }

    public Animation(String icons, Renderizable rend){
        this.renderizable = rend;
        this.renderer = rend.label;
        if(loadSprites(icons)){
            renderer.setIcon(sprites.get(0).icon());
        }
        assert sprites.size()>0 :
                icons+ " no es un nombre de conjunto de Sprites válido," +
                        " utilice el formato \"nombre_[number of sprite]\" ";
    }


    // por cosas de archivos
    private synchronized boolean loadSprites(String icons){
        int i=0;
        while (AssetStore.isThereIcon(icons+separator+i)){
            sprites.add(new SpriteData(icons+separator+i));
            i++;
        }
        return i>0;
    }

    @Override
    public void update() {
        if (frameCounter() % delay != 0) return; //delay

        if (index < sprites.size()) {
            index = index;
        } else {
            if(oneShoot){
                // unchecked
                hide();
                setActive(false);
                gameObject().destroy();
                return;
            }
            else{
            index = 0;
            }
        }
        var sd = sprites.get(index);
        renderer.setIcon(sd.icon());

        index++;
    }

    @Override
    public void setSpeed(float speed) {
        assert speed>0;
        delay = 1 +(int) (60/speed);
    }

    @Override
    public void restart() {
        index = 0;
    }

    @Override
    public int frames() {
        return sprites.size();
    }

    @Override
    public void show() {
        renderizable.show();
    }

    @Override
    public void hide() {
        renderizable.show();
    }

    @Override
    public void setFrame(int frame) {
        assert frame< sprites.size();
        assert frame>=0;
        index = frame;
    }

    @Override
    public Renderizable getRenderizable() {
        return renderizable;
    }





}

package Engine;

import Collisions.HitBox;
import Engine.Components.ITransform;
import RenderingSystem.Renderizable;

import java.util.function.Consumer;

public interface IGameObject {
    HitBox addHitBox(HitBox c);

    <S extends Component> S addComponent(S c);

    Iterable<Component> getComponents();

    void sendMessage(Consumer<Component> consumer);

    <C extends Component> IGameObject addChild(Iterable<C> components) // the only way to create a new gameobject from outside
    ;

    IGameObject addChild() // the only way to create a new gameobject from outside
    ;

    void removeComponent(Component c);

    ITransform transform();

    void update();

    void destroy();

    HitBox getHitbox();

    int size();

    void setRenderer(Renderizable rend);

    Renderizable getRenderer();

    void setOnDestroy(Action onDestroy);
}

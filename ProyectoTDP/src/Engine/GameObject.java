package Engine;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.ITransform;
import Engine.Components.Transform;
import RenderingSystem.Renderizable;

import java.util.*;
import java.util.function.Consumer;

//FIXME uml la herencia. Done
public class GameObject implements IUpdatable, IGameObject {


    // ------ RootControlStuff --------
    private static GameObject root;
    public static IGameObject getRoot()
    {
        if (root == null)
        {
            IEngine eng = TheEngine.getInstance();
            root = new GameObject(null);
            eng.suscribeToUpdate(root);

        }
        return root;
    }


    // ---------- Vars ------------
    private Collection<GameObject> children= new LinkedList<GameObject>();;
    private Collection<Component> components= new LinkedList<Component>();;
    private GameObject parent;
    private Transform transform;

    // --------- Constructor-----------

    private GameObject(GameObject parent)
    {


        transform = new Transform();//each GameObject has a transform

        components.add(transform);
        this.parent = parent;

        if(parent!=null)
        {
            this.parent.children.add(this);
            transform.setFromPrototype(parent.transform);
        }
    }

    //------------- Components Handling --------------

    private HitBox hitbox;
    @Override
    public HitBox addHitBox(HitBox c)
    {
        if(hitbox == null) {
            hitbox = c;
            components.add(c);
            c.setGameObject(this);
            c.start();
        }
        return c;
    }


    @Override
    public<S extends Component> S addComponent(S c)
    {
        assert c != null;
        Core.getInstance().waitForFrames(new Action() {
            @Override
            public void invoke() {
                components.add(c);
            }
        },1);

        c.setGameObject(this);
        c.start();
        return c;
    }
    @Override
    public Iterable<Component> getComponents()
    {
        return components;
    }
    @Override
    public void sendMessage(Consumer<Component> consumer)
    {
        components.forEach(consumer);
    }


    // -------- As a Tree -------


    @Override
    public final<C extends Component> IGameObject addChild(Iterable<C> components) // the only way to create a new gameobject from outside
    {
        IGameObject g = new GameObject(this);
        for (C component : components) {
            g.addComponent(component);
        }
        return g;
    }
    @Override
    public final IGameObject addChild() // the only way to create a new gameobject from outside
    {
        return new GameObject(this);
    }
    @Override
    public void removeComponent(Component c)
    {
        if(components.contains(c))
        {
            components.remove(c);
            c.DestroyComponent();
        }
    }

    @Override
    public ITransform transform()
    {
        return transform;
    }

    @Override
    public void update()
    {
        for (Component component : components) {
            if (component.isActive()) {
                component.update();
            }
        }

        for (GameObject c : new LinkedList<GameObject>(children)) {
            c.update();
        }

    }

    @Override
    public void destroy()
    {
        for (Component c : components) {
            c.setActive(false);
        }
        parent.children.remove(this);
        for (IGameObject IGameObject : new LinkedList<>(children)) {
            IGameObject.destroy();
        }
        children.clear();
        for (Component component : components) {
            component.DestroyComponent();
        }
        if(hitbox !=null)
        {
            HitBoxesManager.getInstance().removeHitBox(hitbox);
        }
        hitbox = null;
        if(onDestroy != null)
            onDestroy.invoke();
    }

    private Action onDestroy;





    @Override
    public HitBox getHitbox()
    {
        return hitbox;
    }

    @Override
    public int size()
    {
        if(children.size()==0)
        {
            return 1;
        }
        else{
            int c = 0;
            for (IGameObject x : children) {
                c += x.size();
            }
            return c;
        }
    }


    private Renderizable renderer;
    @Override
    public void setRenderer(Renderizable rend)
    {
        if(renderer!=rend){

            if(!components.contains(rend))
            {
                addComponent(rend);
            }
            renderer = rend;
        }
    }



    @Override
    public Renderizable getRenderer() {
        return renderer;
    }


    @Override
    public void setOnDestroy(Action onDestroy) {
        this.onDestroy = onDestroy;
    }
}


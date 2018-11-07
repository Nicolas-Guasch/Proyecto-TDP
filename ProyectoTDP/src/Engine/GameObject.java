package Engine;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Components.Transform;
import RenderingSystem.Renderizable;

import java.util.*;
import java.util.function.Consumer;

public class GameObject
{


    // ------ RootControlStuff --------
    private static GameObject root;
    public static GameObject getRoot()
    {
        if (root == null)
        {
            var eng = EngineGetter.Instance().get();
            root = new GameObject(null);
            eng.suscribeToUpdate(root);
            //eng.suscribeToPhysicsUpdate(root);
            // not in this project

        }
        return root;
    }
    private GameObject(){}


    // ---------- Vars ------------
    private Collection<GameObject> children= new LinkedList<>();;
    private Collection<Component> components= new LinkedList<>();;
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
            transform.SetFromPrototype(parent.transform);
        }
    }

    //------------- Components Handling --------------

    private HitBox hitbox;
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


    public<S extends Component> S addComponent(S c)
    {
        assert c != null;
        Core.getInstance().waitForFrames(()->components.add(c),1);

        c.setGameObject(this);
        c.start();
        return c;
    }
    public Iterable<Component> getComponents()
    {
        return components;
    }
    public void sendMessage(Consumer<Component> consumer)
    {
        components.forEach(consumer);
    }


    // -------- As a Tree -------


    public final<C extends Component> GameObject addChild(Iterable<C> components) // the only way to create a new gameobject from outside
    {
        GameObject g = new GameObject(this);
        components.forEach(g::addComponent);
        return g;
    }
    public final GameObject addChild() // the only way to create a new gameobject from outside
    {
        return new GameObject(this);
    }
    public void removeComponent(Component c)
    {
        if(components.contains(c))
        {
            components.remove(c);
            c.DestroyComponent();
        }
    }
    public GameObject getParent()
    {
        return parent;
    }
    public Iterable<GameObject> children()
    {
        return children;
    }
    public Transform transform()
    {
        return transform;
    }

    public void Update()
    {
        components.forEach((c)->{
            if(c.isActive())
            {
                c.update();
            }
        });
        for (GameObject c : new LinkedList<>(children)) {

            c.Update();
        }
        //TODO: hacer cola para add y remove
    }

    public void destroy()
    {
        components.forEach(c->c.setActive(false));
        parent.children.remove(this);
        new LinkedList<>(children).forEach(c->c.destroy());
        children.clear();
        components.forEach(c->c.DestroyComponent());
        if(hitbox !=null)
        {
            HitBoxesManager.getInstance().removeHitBox(hitbox);
        }
        hitbox = null;
        if(onDestroy != null)
            onDestroy.run();
    }

    private Runnable onDestroy;
    public void setOnDestroy(Runnable r)
    {
        onDestroy = r;
    }

    public void SetEnabled(boolean enabled)
    {
        components.forEach(c ->c.setActive(enabled));
        children.forEach(c->c.SetEnabled(enabled));
    }


    public HitBox getHitbox()
    {
        return hitbox;
    }

    public int size()
    {
        if(children.size()==0)
        {
            return 1;
        }
        else{
            int c = 0;
            for (GameObject x : children) {
                c += x.size();
            }
            return c;
        }
    }

    public int SizeComps()
    {
        if(children.size()==0)
        {
            return components.size();
        }
        else{
            int c = 0;
            for (GameObject x : children) {
                c += x.SizeComps();
            }
            return c;
        }
    }


    private Renderizable renderer;
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



    public Renderizable getRenderer() {
        return renderer;
    }


    public Iterable<GameObject> getChildren() {
        return children;
    }


}


package Engine;

import Engine.Components.AbstractCollider;
import Engine.Components.CollidersManager;
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
            root = new GameObject(null);
        }
        return root;
    }
    private GameObject(){}


    // ---------- Vars ------------
    private Collection<GameObject> children;
    private Collection<Component> components;
    private GameObject parent;
    private Transform transform;

    // --------- Constructor-----------

    private GameObject(GameObject parent)
    {
        children = new LinkedList<>();
        components = new LinkedList<>();
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

    private AbstractCollider collider;
    public <S extends AbstractCollider> S addCollider(S c)
    {
        if(collider == null) {
            collider = c;
            components.add(c);
            c.setGameObject(this);
            c.Start();
            CollidersManager.GetInstance().addCollider(collider);
        }
        return c;
    }


    public<S extends Component> S addComponent(S c)
    {
        components.add(c);
        c.setGameObject(this);
        c.Start();
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
    void removeComponent(Component c)
    {
        components.remove(c);
    }
    public GameObject getParent()
    {
        return parent;
    }
    public Iterable<GameObject> children()
    {
        return children;
    }
    public Transform getTransform()
    {
        return transform;
    }

    public void Update()
    {
        components.forEach((c)->{
            if(c.isActive())
            {
                c.Update();
            }
        });
        children.forEach((c)->c.Update());
    }

    public void Destroy()
    {
        components.forEach(c->c.setActive(false));
        parent.children.remove(this);
        new LinkedList<>(children).forEach(c->c.Destroy());
        children.clear();
        components.forEach(c->c.DestroyComponent());
        if(collider!=null)
        {
            CollidersManager.GetInstance().removeCollider(collider);
        }
        collider = null;
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


    public <Col extends AbstractCollider<Col>> Col getCollider()
    {
        return (Col) collider;
    }

    public int Size()
    {
        if(children.size()==0)
        {
            return 1;
        }
        else{
            int c = 0;
            for (GameObject x : children) {
                c += x.Size();
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
    public void addRenderer(Renderizable rend)
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


}


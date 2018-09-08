package Engine;

import Engine.Components.Transform;

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


    public final GameObject addChild(Iterable<Component> components) // the only way to create a new gameobject from outside
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
        components.forEach((c)->c.Update());
        children.forEach((c)->c.Update());
    }

    public void Destroy()
    {
        children.forEach(c->Destroy());
        components.forEach(c->c.DestroyComponent());
    }

    //TODO : Destroy (GameObject g) invocar al ondestroy de los components
    // y broadcastear

}


package Engine;

import Broadcaster.*;
import Components.IPrototype;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameObject implements IPrototype<GameObject>
{









    private List<GameObject> children;
    private Map<Class<Component>,Component> components;
    private List<Component> componentsSorteds; // keep sorted the calls

    private GameObject parent;

    public static Broadcaster<Component> OnComponentCreated;
    private static Invoker<Component> invokerComponentCreated;



    // those both are the dirtiest methods ever
    public <C extends Component<C>> C GetComponent(Class<C> X)
    {
        if(OnComponentCreated == null)
        {
            BroadcasterPackage<Component> p = BroadcasterFactory.GetBroadcaster();
            OnComponentCreated = p.Broadcaster;
            invokerComponentCreated = p.Invoker;
        }
        C ret = null;
        if(components.containsKey(X))
        {
            ret = (C) components.get(X.getClass());
        }
        return ret;
    }
    public <C extends Component> C AddComponent(Class<C> X)
    {
        C instance = null;
        try {
            java.lang.reflect.Constructor<C> cons = X.getConstructor();
            instance = cons.newInstance();
            if(components.containsKey(X))
                throw new DuplicateComponentException("The Game Object already had a component of type "+X.getName());
            components.put((Class<Component>) X,instance);
            componentsSorteds.add(instance);
            instance.setGameObject(this);
            instance.Awake();
            Core.getInstance().AddAnStart(instance);
        }
        catch (Exception e){System.out.println("problems with the engine, Make sure you dont" +
                " try to insert an abstract class or with not public constructor, then.... call Marcos");}
        return instance;
    }

    void Remove(Component c)
    {
        componentsSorteds.remove(c);
        components.remove(c.getClass());
    }

    GameObject(GameObject parent)
    {
        this();
        this.parent = parent;
        this.parent.children.add(this);
    }

    private static GameObject root;
    static GameObject GetRoot()
    {
        if (root != null)
            root = root;
        else
            root = new GameObject();
        return root;
    }

    private Transform _transform;

    public Transform transform(){
        return _transform;
    }

    public GameObject AddLastChild()
    {
        return new GameObject(this);
    }

    private GameObject()
    {
        parent = null;
        children = new LinkedList<>();
        components = new HashMap<>();
        componentsSorteds = new LinkedList<>();
        preorderAwake();
        preorderStart();
        _transform = AddComponent(Transform.class);

    }

    void preorderFixedUpdate(){
        componentsSorteds.forEach((comp)->{
            comp.FixedUpdate(Clock.HasStamp(comp)?Clock.TimeElapsed(comp):0);
            Clock.StampSomething(comp);
        });
        children.forEach((son)->{
            son.preorderFixedUpdate();
        });
    }

    void preorderUpdate() // make it in preorder
    {
        componentsSorteds.forEach((comp)->{
            comp.Update();
        });
        children.forEach((son)->{
            son.preorderUpdate();
        });
    }

    void preorderStart(){
        componentsSorteds.forEach((comp)->{
            Core.getInstance().AddAnStart(comp);
        });
        children.forEach((son)->{
            son.preorderStart();
        });
    }

    void preorderAwake(){
        componentsSorteds.forEach((comp)->{
            comp.Awake();
        });
        children.forEach((son)->{
            son.preorderAwake();
        });
    }

    GameObject getParent() {
        return parent;
    }

    void setParent(GameObject parent) {
        this.parent = parent;
    }

    Iterable<GameObject> children()
    {
        return children;
    }

    @Override
    public GameObject clone()
    {
        if(this == root)
        {
            throw new CoreException("You can't clone the root Game Object");
        }
        GameObject g = new GameObject(parent);
        components.forEach((k,v)->{
            g.AddComponent(k).copy(v);
        });
        return g;
    }


    // ----------- inner classes ----------------
    private class DuplicateComponentException extends RuntimeException {
        public DuplicateComponentException(String s) {
            super(s);
        }
    }
}


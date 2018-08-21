package Engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameObject
{
    private List<GameObject> children;
    private Map<Class<Component>,Component> components;
    private List<Component> componentsSorteds; // keep sorted the calls


    // those both are the dirtiest methods ever
    public <C extends Component> C GetComponent(Class<C> X)
    {
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
        }
        catch (Exception e){System.out.println("problems with the engine, better call Marcos");}
        return instance;
    }

    public GameObject()
    {
        children = new LinkedList<>();
        components = new HashMap<>();
        AddComponent(Transform.class);
    }

    void recursiveUpdate() // make it in preorder
    {
        componentsSorteds.forEach((comp)->{
            comp.Update();
        });
        children.forEach((son)->{
            son.recursiveUpdate();
        });
    }

    void recursiveStart(){
        componentsSorteds.forEach((comp)->{
            Core.getInstance().AddAnStart(comp);
        });
        children.forEach((son)->{
            son.recursiveStart();
        });
    }

    void recursiveAwake(){
        componentsSorteds.forEach((comp)->{
            comp.Awake();
        });
        children.forEach((son)->{
            son.recursiveAwake();
        });
    }

    // ----------- inner classes ----------------
    private class DuplicateComponentException extends RuntimeException {
        public DuplicateComponentException(String s) {
            super(s);
        }
    }
}


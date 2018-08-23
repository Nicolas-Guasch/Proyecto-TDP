package Engine;

import java.util.HashMap;
import java.util.Map;

public class Tester
{




    public static void main (String[] args)
    {
        components = new HashMap<>();
    }
    private static Map<Class<Component>,Component> components;
    public <C extends Component> C AddComponent(Class<C> X)
    {
        C instance = null;
        try {
            java.lang.reflect.Constructor<C> cons = X.getConstructor();
            instance = cons.newInstance();
            if(components.containsKey(X))
                System.out.print("...");
            components.put((Class<Component>) X,instance);
        }
        catch (Exception e){System.out.println("problems with the engine, better call Marcos");}
        return instance;
    }





    public static void testCore1()
    {
        Core nucleo = Core.getInstance();
        GameObject root = GameObject.GetRoot();
        GameObject nuevo = new GameObject(root);
        nuevo.AddComponent(Aver.class);
        nucleo.Start();
    }


}


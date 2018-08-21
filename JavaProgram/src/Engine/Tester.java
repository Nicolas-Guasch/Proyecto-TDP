package Engine;

public class Tester
{


    public static void main (String[] args){
        Core nucleo = Core.getInstance();
        GameObject root = GameObject.GetRoot();
        GameObject nuevo = new GameObject(root);
        nuevo.AddComponent(Aver.class);
        nucleo.Start();

    }

}


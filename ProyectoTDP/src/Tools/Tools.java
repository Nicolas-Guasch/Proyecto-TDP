package Tools;

public class Tools
{

    public static <X> X random(X[] array){
        int i = new java.util.Random().nextInt(array.length);
        return array[i];
    }

}

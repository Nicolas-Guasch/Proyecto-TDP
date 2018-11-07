package Tools;


public class Tools
{

    public static <X> X random(X[] array){
        int i = new java.util.Random().nextInt(array.length);
        return array[i];
    }

    public static<Type> boolean contains( Iterable<Type> iterable,Type element) {
        if(element==null) return false;
        for (Type type : iterable) {
            if((type!=null) && (type==element || type.equals(element))){
                return true;
            }
        }
        return false;
    }
}

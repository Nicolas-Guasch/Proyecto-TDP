import java.util.function.Function;

public class AquiNoHayUnHolaMundo
{    public static void main(String[] args){
        Function p = (Object o) -> {System.out.print(o.toString());return null;};
        p.apply("te lo dije");
    }

}

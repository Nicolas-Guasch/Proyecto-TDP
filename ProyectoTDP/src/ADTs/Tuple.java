package ADTs;

public class Tuple<T1, T2>
{
    private T1 element1;
    private T2 element2;

    public Tuple(T1 element1, T2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public static<TypeA,TypeB> Tuple<TypeA,TypeB>
    get(TypeA c1, TypeB c2)
    {
        return new Tuple<>(c1,c2);
    }

    public T2 get2() {
        return element2;
    }

    public T1 get1() {
        return element1;
    }
}

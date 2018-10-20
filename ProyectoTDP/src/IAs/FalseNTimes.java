package IAs;

import Entities.Entity;

import java.util.function.Predicate;

public class FalseNTimes implements Predicate<Entity> {
    private int n;

    public FalseNTimes(int n) {
        assert n>=0;
        this.n = n;
    }


    @Override
    public boolean test(Entity entity) {
        n--;
        return n<0;
    }
}

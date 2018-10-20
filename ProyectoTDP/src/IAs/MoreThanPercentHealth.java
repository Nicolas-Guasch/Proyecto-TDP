package IAs;

import Entities.Entity;

import java.util.function.Predicate;

        public class MoreThanPercentHealth implements Predicate<Entity> {

            private float toCompare;
            public MoreThanPercentHealth(float initialHealth, float percent){
                toCompare = initialHealth * (percent/100);
            }

    @Override
    public boolean test(Entity entity)
    {
        return entity.data().getHealth()>toCompare;
    }
}

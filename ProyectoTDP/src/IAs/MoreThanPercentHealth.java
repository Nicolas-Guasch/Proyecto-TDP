package IAs;

import Entities.Entity;

import java.util.function.Predicate;
//TODO: falta hacer el que cambia en 50 %
//Seguir el usage en comment de esta clase
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

package IAs;

import ADTs.Vector2;
import Entities.Entity;
import Entities.Ships.PlayerShip;

public class IncreaseSpeedIfWatched extends AIQueryDecorator {

    private final Pilot pilot;
    private final float common_speed;

    public IncreaseSpeedIfWatched(EntityQuery decorated, Pilot pilot) {
        super(decorated);
        this.pilot = pilot;
        this.common_speed = pilot.speed();

    }

    @Override
    public Vector2 whereToMove(Entity ent) {

        Vector2 dir_to_me = ent.referenced().
                            transform().position().
                            minus(PlayerShip.getInstance().
                            referenced().transform().
                            position()).versor();

        var angle = dir_to_me.getUnaryAngle(PlayerShip.getInstance().referenced().transform().top());

        float incmult = 1;

        if(Math.abs(angle)<0.3f){
            incmult = 2;
        }

        pilot.setSpeed(incmult*common_speed);




        return decorated.whereToMove(ent);
    }

    @Override
    public Vector2 whereToSee(Entity ent) {
        return decorated.whereToSee(ent);
    }
}

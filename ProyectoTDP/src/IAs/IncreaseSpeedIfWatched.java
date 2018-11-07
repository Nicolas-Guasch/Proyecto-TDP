package IAs;

import ADTs.Vector2;
import Entities.Entity;
import Entities.Ships.Player.PlayerShip;

public class IncreaseSpeedIfWatched extends AIQueryDecorator {

    private final Pilot pilot;
    private final float common_speed;
    private float degrees;

    public IncreaseSpeedIfWatched(EntityQuery decorated, Pilot pilot) {
        super(decorated);
        this.pilot = pilot;
        this.common_speed = pilot.speed();
        degrees = 0.3f;
    }

    public IncreaseSpeedIfWatched(EntityQuery handler, Pilot pilot, float degrees) {
        this(handler,pilot);
        this.degrees = degrees;
    }

    @Override
    public Vector2 whereToMove(Entity ent) {

        Vector2 dir_to_me = ent.referenced().
                            transform().position().
                sub(PlayerShip.getInstance().
                            referenced().transform().
                            position()).norma();

        var angle = dir_to_me.getUnaryAngle(PlayerShip.getInstance().referenced().transform().top());

        float incmult = 1;

        if(Math.abs(angle)<degrees){
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

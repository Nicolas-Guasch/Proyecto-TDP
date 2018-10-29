package Entities.Obstacles;

import CuteThings.Explos;
import Engine.Components.Transform;

public class ThenVolatile implements Runnable {


    private final Transform transform;
    private String volatileName;

    public ThenVolatile(Transform transform, String volatileName) {
        this.transform = transform;
        this.volatileName = volatileName;
    }

    @Override
    public void run() {
        Explos.getInstance().getVolatile(transform.position(), volatileName,25);
    }
}

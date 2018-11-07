package Entities.Barricades;

import CuteThings.AnimatorsVolatiles;
import Engine.Components.Transform;

public class ThenVolatile implements Runnable {


    private final Transform transform;
    private String volatileName;

    ThenVolatile(Transform transform, String volatileName) {
        this.transform = transform;
        this.volatileName = volatileName;
    }

    @Override
    public void run() {
        var t = AnimatorsVolatiles.getInstance().getVolatile(transform.position(), volatileName,25);
        t.setTop(transform.top());
    }
}

package Entities.Barricades;

import Engine.Action;
import Tools.AnimatorsVolatiles;
import Engine.Components.Transform;

public class ThenVolatile implements Action { //fixme (herencia y run cambia por invoke)


    private final Transform transform;
    private String volatileName;

    ThenVolatile(Transform transform, String volatileName) {
        this.transform = transform;
        this.volatileName = volatileName;
    }

    @Override
    public void invoke() {
        Transform t = AnimatorsVolatiles.getInstance().getVolatile(transform.position(), volatileName,25);
        t.setTop(transform.top());
    }
}

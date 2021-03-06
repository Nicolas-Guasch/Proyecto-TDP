package Entities.Barricades;

import Engine.Action;
import Engine.Components.ITransform;
import Tools.AnimatorsVolatiles;

public class ThenVolatile implements Action {


    private final ITransform ITransform;
    private String volatileName;

    ThenVolatile(ITransform ITransform, String volatileName) {
        this.ITransform = ITransform;
        this.volatileName = volatileName;
    }

    @Override
    public void invoke() {
        ITransform t = AnimatorsVolatiles.getInstance().getVolatile(ITransform.position(), volatileName,25);
        t.setTop(ITransform.top());
    }
}

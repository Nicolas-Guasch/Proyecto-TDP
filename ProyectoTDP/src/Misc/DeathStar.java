package Misc;

import Engine.GameObject;
import ADTs.Vector2;
import GameData.GameSettings;
import Scripts.AlwaysLateral;
import Scripts.AlwaysRotate;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.Directionable;
import UtilsBehaviours.MirrorBounds;

public class DeathStar {

    public DeathStar() {

    }

    public GameObject get() {
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        GameObject deathStar = GameObject.getRoot().addChild();
        deathStar.getTransform().setPosition(new Vector2(-500, 400));


        deathStar.addComponent(new MirrorBounds(topRight.prod(1.4f), bottomLeft.prod(1.4f)));
        deathStar.addComponent(new Directionable(Vector2.Random(0.5f)));
        deathStar.addComponent(new AlwaysRotate(0.2f));
        deathStar.getTransform().setZcomponent(493);Renderizable death = new Renderizable(new SpriteData("DeathStar", new Vector2(500, 500)));
        death.show();
        deathStar.addComponent(death);
        return deathStar;
    }
}


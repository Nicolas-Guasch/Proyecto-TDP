package Misc;

import Engine.GameObject;
import ADTs.Vector2;
import GameData.GameSettings;
import Scripts.AlwaysRotate;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Scripts.Directionable;
import UtilsBehaviours.MirrorBounds;

public class DeathStar {

    private static GameObject instance;

    public static GameObject get() {
        if (instance!=null)return instance;

        GameObject deathStar = GameObject.getRoot().addChild();
        deathStar.transform().setPosition(new Vector2(-500, 400));
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        deathStar.addComponent(new MirrorBounds(topRight.prod(1.4f), bottomLeft.prod(1.4f)));
        deathStar.addComponent(new Directionable(Vector2.random(0.5f)));
        deathStar.addComponent(new AlwaysRotate(0.2f));
        deathStar.transform().setZcomponent(493);Renderizable death = new Renderizable(new SpriteData("deathstar", new Vector2(500, 500)));
        death.show();
        deathStar.addComponent(death);
        instance = deathStar;
        return deathStar;
    }
}


package Misc;

import Engine.GameObject;
import Engine.Vector2;
import GameData.GameSettings;
import Scripts.AlwaysLateral;
import Scripts.AlwaysRotate;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Stuff.Paths;
import UtilsBehaviours.MirrorBounds;

public class DeathStar {

    public DeathStar() {

    }

    public void get() {
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        GameObject deathStar = GameObject.getRoot().addChild();
        deathStar.getTransform().setPosition(new Vector2(-500, 400));


        deathStar.addComponent(new MirrorBounds(topRight.prod(1.4f), bottomLeft.prod(1.4f)));
        deathStar.addComponent(new AlwaysLateral(Vector2.Random()));
        deathStar.addComponent(new AlwaysRotate(0.04f));
        deathStar.getTransform().setZcomponent(93);Renderizable death = new Renderizable(new SpriteData(Paths.DeathStar, new Vector2(500, 500)));
        death.Show();deathStar.addComponent(death);
    }
}


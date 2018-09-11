package Ships.Builder;

import Engine.Components.RectangleCollider;
import Engine.GameObject;
import Engine.Vector2;
import GameData.GameSettings;
import InputManager.DiscreteClick;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Ships.Bullets.*;
import Ships.Entities.SoloShooter;
import Stuff.Paths;
import UtilsBehaviours.MirrorBounds;
import UtilsBehaviours.MouseFollower;

public class SoloShipBuilder extends ShipBuilder
{
    private static float SoloBulletsSpeed = 34;
    private AbstractBulletLauncher launcher;
    private GameObject solo;

    private SpriteData BulletData = new SpriteData(Paths.LaserBlue, new Vector2(15,60));
    private SpriteData SoloData =  new SpriteData(Paths.Alcon, new Vector2(100, 100));

    public SoloShipBuilder()
    {
        solo = GameObject.getRoot().addChild();
    }

    @Override
    public void createRenderer()
    {
        Renderizable rendSolo = new Renderizable(SoloData);
        solo.addComponent(rendSolo);
        rendSolo.Show();
    }

    @Override
    public void createCollider()
    {
        solo.addCollider(new RectangleCollider(new Vector2(40,40)));
    }

    @Override
    public void createController()
    {
        solo.addComponent(new SoloDrive());
        ship.setLauncher(launcher);
        ship.setReferenced(solo);
        solo.addComponent(new MouseFollower());
        solo.addComponent(new MirrorBounds(GameSettings.GetInstance().bounds().prod(1.3f)));
        solo.getTransform().setZcomponent(-8);
        solo.getTransform().setPosition(new Vector2(0, -650));
    }

    @Override
    public void createBulletLauncher()
    {
        //SoloShooter shooter = new SoloShooter(new DiscreteClick(1), launcher);
        //solo.addComponent(shooter);
        //ship.setShooter(shooter);

    }
}

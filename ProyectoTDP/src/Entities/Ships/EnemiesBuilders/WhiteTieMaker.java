package Entities.Ships.EnemiesBuilders;

import Collisions.HitBox;
import Collisions.HitBoxesManager;
import Engine.Vector2;
import Entities.Behaviours.FireFrequency;
import Entities.Behaviours.LookTarget;
import Entities.Builders.Concretes.TieBulletBuilder;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.EnemyBulletBuilder;
import Entities.EnemyBullet;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.PlayerShip;
import Entities.Weapons.EnemyShootFront;
import Entities.Weapons.WeaponSet;
import GameData.GameSettings;
import RenderingSystem.RenderingTools;
import RenderingSystem.Renderizable;
import RenderingSystem.SpriteData;
import Tools.Random;
import UtilsBehaviours.MirrorBounds;

public class WhiteTieMaker extends EnemyShipBuilder
{


    @Override
    public void assembleSprite() {
        SpriteData data = new SpriteData("whitetie");
        Renderizable rend = new Renderizable(data);
        rend.show();
        ship.setRenderer(rend);
    }

    @Override
    public void assembleHitBox() {
        HitBox hb = HitBox.getOne(100,100,ship);
        HitBoxesManager.getInstance().addHitBox(hb, HitBoxesManager.ENEMIES);
        ship.setHitBox(hb);
    }

    @Override
    public void assembleWeapons() {
        int phaseshift = 18;

        // -------------- creo el arma y la agrego ---------------
        BulletDirector<EnemyBullet, EnemyBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new TieBulletBuilder(ship.getReferenced().getTransform()));
        EnemyShootFront esf = new EnemyShootFront(phaseshift,director,ship.getReferenced().getTransform());
        ship.addWeapon(esf);

        // --------------- configuro el arma para disparar cada 40 frames
        int freq = 40;
        WeaponSet bp = ship.getBagpack();
        FireFrequency fireFrequency = new FireFrequency(freq,bp); // hace qeu dispare cada freq frames
        ship.addBehaviour(fireFrequency);

        // --------------- configuro el piloto -----------------
        //GameSettings.GetInstance().getSpeed("whitetie");

        // ---------------- hago que mire hacia abajo ----------
        ship.getReferenced().getTransform().setTop(Vector2.DOWN());
        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        Vector2 topRight = bottomRight.mirrorX();
        Vector2 bottomLeft = bottomRight.mirrorY();
        ship.addBehaviour(new MirrorBounds(topRight.prod(1.2f),bottomLeft.prod(1.2f)));
        // ----------------- que alguna nave random mire al player ------------
        ship.addBehaviour(new LookTarget(PlayerShip.getInstance().getReferenced().getTransform()));
    }

    @Override
    public void assembleBehaviours() {

    }

    @Override
    public void assembleData() {

    }
}

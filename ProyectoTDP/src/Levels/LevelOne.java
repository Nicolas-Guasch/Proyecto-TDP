/*
package Levels;
import Entities.*;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Rewards.WeaponReward;
import Entities.Ships.*;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.Weapon;
import UI.*;
import Engine.*;
import Engine.Components.Transform;
import Entities.Behaviours.PlayerLateralController;
import Entities.Builders.Concretes.*;
import GameData.GameSettings;
import RenderingSystem.RenderingTools;

import java.util.concurrent.Callable;


public class LevelOne {

    private LevelOne(){}

    private static LevelOne inst;
    public static LevelOne Instance()
    {
        inst = inst==null ? new LevelOne():inst;
        return inst;
    }

    private Vector2 topRight;
    private Vector2 bottomLeft;

    private EnemyShipDirector ties;
    private EnemyShipBuilder builder;
    private TheGrimReaper lifeControl;
    public PlayerShip player;
    public EnemyShip currentBoss;
    private Component oldControl;

    public void runLevel() {

        lifeControl = TheGrimReaper.Instance();

        initializePlayer();


        // just for testing

        GameObject premio = GameObject.getRoot().addChild();
        WeaponReward rew = new WeaponReward(premio);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.getReferenced().getTransform()));
        Weapon wea = new AngularWeapon<>(player.getReferenced().getTransform(),director,5);
        rew.setWeapon(wea);
        premio.getTransform().setPosition(new Vector2(0,400));
        rew.setData(new EntityData(100,100,100));
        TheGrimReaper.Instance().add(rew);

         // end just for testing

        ties = new EnemyShipDirector();

        Callable<Boolean> complete = UI.getInstance().startLevel(0);

        builder = new LinedTIEBuilder();
        new DoWhen(complete,()->
        {
            SendWave(2);
        });
    }

    private int currentEnemies;

    public void SendWave(int cant)
    {
        LinedWave wave = new LinedWave();
        ties.setBuilder(builder);
        Iterable<EnemyShip> it = wave.addEnemies(ties,cant);
        wave.run();
        it.forEach(e-> TheGrimReaper.Instance().add(e));
        currentEnemies+=2;
        new DoWhen(wave::EmptyWave ,this::Next);
    }

    private void Next() {
        EngineGetter.Instance().get().WaitForFrames(() ->
        {
            if(currentEnemies % 10 != 0)
            {
                if(currentEnemies %3 == 1)
                {
                    builder = new TieMediumBuilder();
                    SendWave((currentEnemies/5)+1);
                }
                else
                {
                    builder = new LinedTIEBuilder();
                    SendWave(currentEnemies);
                }
            }
            else
            {
                builder = new TieHardBuilder();
                SendWave((currentEnemies/6)+2);
            }


        }, 30);
    }

    private void initializePlayer() {
        PlayerShipDirector pdir = new PlayerShipDirector();
        pdir.setBuilder(new PlayerShipMaker());
        pdir.create();
        pdir.assemble();
        player = pdir.get();
        player.getReferenced().getTransform().setPosition(new Vector2(0, -300));

        lifeControl.add(player); // si te matan chau
        player.getReferenced().getTransform().setZcomponent(-20);

        Vector2 bottomRight = RenderingTools.CanvasToWorld(GameSettings.GetInstance().sizeWindow);
        topRight = bottomRight.mirrorX();
        bottomLeft = bottomRight.mirrorY();
        oldControl = new PlayerLateralController(GameSettings.GetInstance().SoloSpeed);
        player.addBehaviour(oldControl);
    }


    private void sendBoss()
    {
        builder = new FirstBossBuilder();
        ties.setBuilder(builder);
        ties.create();
        ties.assemble();
        currentBoss = ties.get();

        Transform tr = currentBoss.getReferenced().getTransform();

        tr.setZcomponent(-10);
        tr.setPosition(new Vector2(200,250));

        lifeControl.add(currentBoss);


    }

}

*/
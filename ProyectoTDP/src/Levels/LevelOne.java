package Levels;
import UI.*;
import Engine.*;
import Engine.Components.Transform;
import Entities.Behaviours.PlayerLateralController;
import Entities.Builders.Concretes.*;
import Entities.Builders.Directors.EnemyShipDirector;
import Entities.Builders.Directors.PlayerShipDirector;
import Entities.Builders.EnemyShipBuilder;
import Entities.EnemyShip;
import Entities.PlayerShip;
import Entities.Reaper;
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
    private Reaper lifeControl;
    public PlayerShip player;
    public EnemyShip currentBoss;
    private Component oldControl;

    public void runLevel() {

        lifeControl = Reaper.Instance();

        initializePlayer();


        ties = new EnemyShipDirector();

        Callable<Boolean> complete = UI.getInstance().startLevel(0);

        builder = new LinedTIEBuilder();
        var dowh = new DoWhen(complete,()->
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
        Component runner = wave.run();
        GameObject.getRoot().addChild().addComponent(runner);
        it.forEach(e-> Reaper.Instance().add(e));
        currentEnemies+=2;
        new DoWhen(wave::EmptyWave ,this::Next);
    }

    private void Next() {
        EngineFactory.Instance().get().WaitForFrames(() ->
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
        pdir.setBuilder(new PlayerBuilder(3));
        pdir.create();
        pdir.assemble();
        player = pdir.get();
        player.getReferenced().getTransform().setPosition(new Vector2(0, -300));
        player.getReferenced().getTransform().setTop(Vector2.UP());
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

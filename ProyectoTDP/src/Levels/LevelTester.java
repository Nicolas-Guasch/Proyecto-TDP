package Levels;
import Entities.*;
import Entities.Builders.Directors.BulletDirector;
import Entities.Builders.PlayerBulletBuilder;
import Entities.Rewards.WeaponReward;
import Entities.Ships.*;
import Entities.Weapons.AngularWeapon;
import Entities.Weapons.Weapon;
import InputManager.AbstractDiscreteInput;
import InputManager.DiscreteClick;
import InputManager.DiscreteKeyInput;
import UI.*;
import Engine.*;
import Engine.Components.Transform;
import Entities.Behaviours.PlayerLateralController;
import Entities.Builders.Concretes.*;
import GameData.GameSettings;
import RenderingSystem.RenderingTools;

import java.util.concurrent.Callable;


public class LevelTester extends Component {

    private LevelTester() {
    }

    private static LevelTester inst;

    public static LevelTester Instance() {
        if (inst == null) {
            inst = new LevelTester();
            GameObject.getRoot().addChild().addComponent(inst);
        }

        return inst;
    }


    private LinedWave wave;
    private EnemyShipDirector ties;
    private EnemyShipBuilder builder;
    private TheGrimReaper lifeControl;
    public PlayerShip player;

    private Component oldControl;
    private AbstractDiscreteInput SendWaveKey;



    private void initializePlayer() {
        var pdir = new PlayerShipDirector();
        pdir.setBuilder(new PlayerShipMaker());
        pdir.create();
        pdir.assemble();
        player = pdir.get();
        player.getReferenced().getTransform().setPosition(new Vector3(0, -300,-20));
        lifeControl.add(player);
    }


    public void runLevel() {

        lifeControl = TheGrimReaper.Instance();
        initializePlayer();



        // --------  premio -------

        GameObject premio = GameObject.getRoot().addChild();
        WeaponReward rew = new WeaponReward(premio);
        BulletDirector<PlayerBullet, PlayerBulletBuilder> director = new BulletDirector<>();
        director.setBuilder(new BulletPlayerBuilder(player.getReferenced().getTransform()));
        Weapon wea = new AngularWeapon<>(player.getReferenced().getTransform(),director,5);
        rew.setWeapon(wea);
        premio.getTransform().setPosition(new Vector2(0,400));
        rew.setData(new EntityData(100,100,100));
        TheGrimReaper.Instance().add(rew);

        // --------- enemies stuff -------

        wave  = new LinedWave();
        ties = new EnemyShipDirector();
        var complete = UI.getInstance().startLevel(0);
        builder = new TieMediumBuilder();
        new DoWhen(complete,this::SendWave);

    }


    public void SendWave()
    {


        ties.setBuilder(builder);
        Iterable<EnemyShip> it = wave.addEnemies(ties,8);
        wave.run();
        it.forEach(e-> TheGrimReaper.Instance().add(e));
    }

}

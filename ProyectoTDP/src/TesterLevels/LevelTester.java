package TesterLevels;
import Entities.*;
import Entities.Ships.*;
import InputManager.AbstractDiscreteInput;
import Misc.DeathStar;
import UI.*;
import Engine.*;
import Entities.Builders.Concretes.*;


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



        // --------- enemies stuff -------

        wave  = new LinedWave();
        ties = new EnemyShipDirector();
        var complete = UI.getInstance().startLevel(0);
        builder = new TieMediumBuilder();
        new DoWhen(complete,this::SendWave);



        /// ------------- dEATH sTAR ----------

        new DeathStar().get();


    }


    public void SendWave()
    {


        ties.setBuilder(builder);
        Iterable<EnemyShip> it = wave.addEnemies(ties,8);
        wave.run();
        it.forEach(e-> TheGrimReaper.Instance().add(e));
    }

}
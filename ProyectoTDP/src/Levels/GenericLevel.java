package Levels;

import Engine.Component;
import Entities.Ships.EnemyShip;
import Entities.Ships.EnemyShipBuilder;
import Entities.Ships.EnemyShipDirector;
import Entities.Ships.PlayerShip;
import TesterLevels.Vector3;

import java.util.Collection;

public class GenericLevel extends Component implements ILevel {

    protected EnemyShipDirector Creator;
    protected EnemyShipBuilder[] builders;
    private Collection<Vector3> initial_positions;




    private float z = -10; // z component for enemies;
    private Runnable onWin, onLoose;






    public GenericLevel(Collection<Vector3> initial_positions, EnemyShipBuilder[] builders) {
        this.initial_positions = initial_positions;
        this.builders = builders;
        Creator = new EnemyShipDirector();
    }

    protected EnemyShip create() {
        Creator.create();
        Creator.assemble();
        return Creator.get();
    }


    /**
     * requires player initialized
     * @param onWin action to play on win the level
     * @param onLoose action to play on player loose
     */
    @Override
    public void run(Runnable onWin, Runnable onLoose) {
        this.onLoose = onLoose;
        this.onWin = onWin;
        createEnemies();
        createObstacles();
        setActive(true);
    }

    private void createObstacles() {

    }

    private void createEnemies() {

    }


    @Override
    public void Update() {

    }
}

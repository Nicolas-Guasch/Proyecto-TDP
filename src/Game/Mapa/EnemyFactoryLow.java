package Game.Mapa;

import Game.Units.Enemies.Enemy;

public class EnemyFactoryLow extends EnemyFactory{

    @Override
    public Enemy createEnemy() {
        // Aca va la creación de los enemigos bajo el criterio que queramos, en este caso enemigos de bajo nivel.
        return null;
    }
}

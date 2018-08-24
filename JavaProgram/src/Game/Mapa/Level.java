package Game.Mapa;

public abstract class Level {
    protected EnemyFactory enemyFactory;
    protected int cantEnemies;
    protected int killedEnemies=0;

    public Level(EnemyFactory factory, int enemies){
        cantEnemies=enemies;
        enemyFactory= factory;
    }

    public int RemainingEnemies(){
        return cantEnemies-killedEnemies;
    }

    public EnemyFactory GetFactory(){
        return enemyFactory;
    }
    //True si no hay más enemigos.
    public boolean KillEnemy(){
        killedEnemies++;
        return RemainingEnemies()==0;
    }

    // Se debe retornar el proximo nivel. Si no hay próximo se retorna nulo
    public abstract Level NextLevel();



}

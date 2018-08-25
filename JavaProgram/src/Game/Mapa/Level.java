package Game.Mapa;

public class Level {
    protected EnemyFactory enemyFactory;
    protected int remainingEnemies;
    protected int killedEnemies;
    protected boolean levelEnd;

    public Level(){
        killedEnemies=0;
        remainingEnemies=20;
        enemyFactory= new EnemyFactoryLow();
    }

    public int GetRemainingEnemies(){
        return remainingEnemies;
    }

    public EnemyFactory GetFactory(){
        return enemyFactory;
    }
    //True si no hay más enemigos.
    public void KillEnemy(){
        killedEnemies++;
        if(remainingEnemies==0){
            levelEnd=true;// Observer Notificar fin de nivel
        }
    }

    // Se debe retornar el proximo nivel. Si no hay próximo se retorna nulo
    public void NextLevel(){
        if(levelEnd){
            //Aumentar enemigos o cambiar la factory
        }
    }



}

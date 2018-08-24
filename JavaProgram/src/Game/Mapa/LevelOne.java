package Game.Mapa;

public class LevelOne extends Level{

    public LevelOne() {
        super(new EnemyFactoryLow(), 20);
    }

    @Override
    public Level NextLevel() {
        return null;
    }
}

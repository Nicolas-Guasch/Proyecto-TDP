package TesterLevels;

import Entities.Ships.EnemyShip;
import GenericVisitor.MonoVisitor;

public class EnemiesManager
{
    private static EnemiesManager instance;

    public static EnemiesManager getInstance()
    {
        if (instance == null)
        {
            instance = new EnemiesManager();
        }
        return instance;
    }

    private EnemiesManager()
    {
        currentStructure = new EnemiesList();
    }

    private EnemiesStructure currentStructure;

    public EnemiesStructure getStructure()
    {
        return currentStructure;
    }

    public void computeOperation(MonoVisitor<EnemyShip> visitor)
    {
        currentStructure.forEach(visitor::visit);
    }


}

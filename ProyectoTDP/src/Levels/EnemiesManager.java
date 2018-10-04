package Levels;

import Entities.Ships.EnemyShip;
import GenericVisitor.Visitor;

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

    public void computeOperation(Visitor<EnemyShip> visitor)
    {
        currentStructure.forEach(visitor::visit);
    }


}

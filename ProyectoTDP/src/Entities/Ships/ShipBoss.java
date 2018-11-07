package Entities.Ships;

import Engine.GameObject;
import Entities.Weapons.Arsenal;
import Entities.Weapons.EnemyArsenal;
import EntitiesVisitor.BossVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.CurrentMatchData;
import GameData.GameSettings;

public class ShipBoss extends BaseEnemyShip {



    public ShipBoss(GameObject referenced)
    {
        super(referenced,new EnemyArsenal());
        visitor = new BossVisitor(this);
        data = GameSettings.GetInstance().TieData.clone();
    }

    public void accept(VisitorEntity visitor) {
        visitor.visit(this);
    }

    public Arsenal getBagPack() {
        return weapons;
    }

    @Override
    public void onDeath()
    {
        super.onDeath();
        //luce harcode

        CurrentMatchData.getMatchData().incScore(1);
    }


}

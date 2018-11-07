package Entities.Ships;

import Engine.GameObject;
import Entities.Weapons.EnemyArsenal;
import EntitiesVisitor.EnemyVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.CurrentMatchData;
import GameData.GameSettings;

public class EnemyShip extends BaseEnemyShip {



	public EnemyShip(GameObject referenced)
	{
		super(referenced,new EnemyArsenal());
		visitor = new EnemyVisitor(this);
		data = GameSettings.GetInstance().TieData.clone();
	}

	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}



	@Override
	public void onDeath()
	{
		super.onDeath();
		//luce harcode

		CurrentMatchData.getMatchData().incScore(1);
	}


}

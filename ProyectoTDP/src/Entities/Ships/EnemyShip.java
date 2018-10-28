package Entities.Ships;

import ADTs.Vector2;
import CuteThings.Explos;
import Engine.GameObject;
import Entities.Weapons.EnemyArsenal;
import Entities.Weapons.Arsenal;
import EntitiesVisitor.EnemyVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.CurrentMatchData;
import GameData.GameSettings;

public class EnemyShip extends Ship {



	public EnemyShip(GameObject referenced)
	{
		super(referenced,new EnemyArsenal());
		visitor = new EnemyVisitor(this);
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

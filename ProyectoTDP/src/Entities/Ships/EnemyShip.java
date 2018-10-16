package Entities.Ships;

import Collisions.CollisionData;
import Engine.GameObject;
import Entities.EnemyBullet;
import Entities.BarricadeBoth;
import Entities.BarricadeEnem;
import Entities.PlayerBullet;
import Entities.Rewards.Reward;
import Entities.Weapons.EnemyBagpack;
import Entities.Weapons.WeaponSet;
import EntitiesVisitor.EnemyVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.CurrentMatchData;
import GameData.GameSettings;
import GenericVisitor.MonoVisitor;

public class EnemyShip extends Ship {



	public EnemyShip(GameObject referenced)
	{
		super(referenced,new EnemyBagpack());
		visitor = new EnemyVisitor(this);
		data = GameSettings.GetInstance().TieData;
	}

	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}

	public WeaponSet getBagPack() {
		return weapons;
	}

	@Override
	public void onDeath()
	{
		super.onDeath();
		CurrentMatchData.getMatchData().incScore(1);
	}


}

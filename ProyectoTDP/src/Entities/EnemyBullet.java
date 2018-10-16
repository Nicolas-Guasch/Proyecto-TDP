package Entities;

import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.EnemyBulletVisitor;
import EntitiesVisitor.VisitorEntitie;
import GenericVisitor.MonoVisitor;


public class EnemyBullet extends Bullet {

	public EnemyBullet(GameObject referenced) {
		super(referenced);
		visitor = new EnemyBulletVisitor(this);
	}

	@Override
	public void accept(VisitorEntitie visitor) {
		visitor.visit(this);
	}

}

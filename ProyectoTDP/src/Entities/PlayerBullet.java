package Entities;

import Collisions.CollisionData;
import Engine.GameObject;
import Entities.Rewards.Reward;
import Entities.Ships.EnemyShip;
import Entities.Ships.PlayerShip;
import EntitiesVisitor.PlayerBulletVisitor;
import EntitiesVisitor.VisitorEntitie;
import GenericVisitor.MonoVisitor;

public class PlayerBullet extends Bullet {
	public PlayerBullet(GameObject referenced) {
		super(referenced);
		visitor = new PlayerBulletVisitor(this);
	}
	@Override
	public void accept(VisitorEntitie visitor) {
		visitor.visit(this);
	}
}

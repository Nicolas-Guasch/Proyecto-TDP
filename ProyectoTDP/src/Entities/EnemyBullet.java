package Entities;

import Engine.IGameObject;
import EntitiesVisitor.EnemyBulletVisitor;
import EntitiesVisitor.VisitorEntity;


public class EnemyBullet extends Bullet {

	public EnemyBullet(IGameObject referenced) {
		super(referenced);
		visitor = new EnemyBulletVisitor(this);
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}

}

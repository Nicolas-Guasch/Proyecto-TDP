package Entities;

import Engine.GameObject;
import EntitiesVisitor.EnemyBulletVisitor;
import EntitiesVisitor.VisitorEntity;


public class EnemyBullet extends Bullet {

	public EnemyBullet(GameObject referenced) {
		super(referenced);
		visitor = new EnemyBulletVisitor(this);
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}

}

package Entities;

import Engine.GameObject;
import EntitiesVisitor.PlayerBulletVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;

public class PlayerBullet extends Bullet {
	public PlayerBullet(GameObject referenced) {
		super(referenced);
		visitor = new PlayerBulletVisitor(this);
		data = GameSettings.GetInstance().PlayerBulletData;
	}
	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}
}

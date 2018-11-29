package Entities;

import Engine.IGameObject;
import EntitiesVisitor.PlayerBulletVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;

public class PlayerBullet extends Bullet {
	public PlayerBullet(IGameObject referenced) {
		super(referenced);
		visitor = new PlayerBulletVisitor(this);
		data = GameSettings.GetInstance().PlayerBulletData;
	}
	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}
}

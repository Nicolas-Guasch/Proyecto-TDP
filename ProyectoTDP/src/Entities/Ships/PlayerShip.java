package Entities.Ships;
import Collisions.CollisionData;
import Engine.GameObject;
import Entities.EnemyBullet;
import Entities.Rewards.Reward;
import Entities.Weapons.PlayerBagpack;
import GameData.LostOrWin;
import Audio.SoundManager;
import GenericVisitor.MonoVisitor;
import InputManager.DiscreteClick;
import InputManager.DiscreteKeyInput;
import UI.UI;

public class PlayerShip extends Ship<PlayerShip> {


	private static PlayerShip instance;

	public static PlayerShip getInstance()
	{
		return instance;
	}

	public static boolean isInitialited() {
		return instance != null;
	}

	public static void initialize(GameObject go) {
		assert !isInitialited();
		instance = new PlayerShip(go);
	}

	@Override
	public void onDeath() {
		super.onDeath();
		LostOrWin.getInstance().MakeGameOver();
	}

	private PlayerShip(GameObject referenced) {

		//super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteKeyInput(" cC")));

		super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteClick(1)));

		SoundManager.Instance().setTransformListener(this.getReferenced().getTransform());
	}

	public void collideWith(EnemyShip enemyShip)
	{
		float damage = enemyShip.getData().getDamage() - data.getShield();
		damage = damage>=0 ? damage : 0;
		setLife(data.getHealth() - damage);
	}

	public void collideWith(EnemyBullet ent)
	{
		float damage = ent.getData().getDamage() - data.getShield();
		damage = damage>=0 ? damage : 0;
		setLife(data.getHealth() - damage);
	}


	public void collideWith(Reward ent)  {

	}

	@Override
	public void reportCollision(CollisionData data)
	{
		data.Their().collideWith(this);
	}


	public void setLife(float cantLife)
	{
		data.setHealth(cantLife);
		UI.getInstance().PlayerLife((int) cantLife);
	}

	@Override
	public void accept(MonoVisitor<PlayerShip> playerShipVisitor) {
		playerShipVisitor.visit(this);
	}
}

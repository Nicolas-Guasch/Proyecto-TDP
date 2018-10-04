package Entities.Ships;
import Collisions.CollisionData;
import Engine.GameObject;
import Entities.EnemyBullet;
import Entities.Rewards.Reward;
import Entities.Weapons.PlayerBagpack;
import Exceptions.PlayerUninitializedException;
import GameData.GameOver;
import GameData.SoundManager;
import GenericVisitor.Visitor;
import InputManager.DiscreteClick;
import InputManager.DiscreteKeyInput;
import UI.UI;

public class PlayerShip extends Ship<PlayerShip> {

	//TODO: is the only player on scene must be a singleton
	private static PlayerShip instance;
	public static void initialize(GameObject gameObject)
	{
		if(instance==null)
		{
			instance = new PlayerShip(gameObject);
		}
	}
	public static PlayerShip getInstance()
	{
		if(instance==null)
			throw new PlayerUninitializedException("You must initialize your player with Initialize(GameObject gameObject)");
		return instance;
	}

	public static boolean isInitialited() {
		return instance != null;
	}

	@Override
	public void onDeath() {
		super.onDeath();
		GameOver.getInstance().MakeGameOver();
	}

	private PlayerShip(GameObject referenced) {
		super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteClick(1)));

		SoundManager.Instance().setTransformListener(this.getReferenced().getTransform());
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
	public void accept(Visitor<PlayerShip> playerShipVisitor) {
		playerShipVisitor.visit(this);
	}
}

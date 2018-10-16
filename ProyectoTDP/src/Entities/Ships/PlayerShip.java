package Entities.Ships;
import Engine.GameObject;
import Entities.Weapons.PlayerBagpack;
import EntitiesVisitor.PlayerVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;
import GameData.MatchResult;
import Audio.SoundManager;
import InputManager.DiscreteClick;
import InputManager.DiscreteKeyInput;
import UI.UI;
import UtilsBehaviours.PlayerWatcher;

public class PlayerShip extends Ship {


	private static PlayerShip instance;
	public static PlayerShip getInstance()
	{
		return instance;
	}
	public static boolean isUninitialized() {
		return instance == null;
	}

	public static void initialize(GameObject go) {
		assert isUninitialized();
		instance = new PlayerShip(go);
		go.addComponent(new PlayerWatcher(instance));
	}

	@Override
	public void onDeath()
	{
		super.onDeath();
		MatchResult.getInstance().EmpireWins();
	}

	private PlayerShip(GameObject referenced) {
		//super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteClick(1)));
		super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteKeyInput(" ")));
		SoundManager.Instance().setTransformListener(this.referenced().transform());
		visitor = new PlayerVisitor();
		data = GameSettings.GetInstance().PlayerData;
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}

	public void setLife(float cantLife)
	{
		data.setHealth(cantLife);
		UI.getInstance().playerLife((int) cantLife);
	}


}

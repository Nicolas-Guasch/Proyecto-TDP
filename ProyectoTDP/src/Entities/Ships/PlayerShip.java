package Entities.Ships;

import ADTs.Vector2;
import Engine.GameObject;
import Entities.Weapons.PlayerBagpack;
import Entities.Weapons.WeaponSet;
import EntitiesVisitor.PlayerVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;
import GameData.MatchResult;
import Audio.SoundManager;
import InputManager.DiscreteClick;
import InputManager.DiscreteKeyInput;

import Mementos.IMementoPlayer;
import Mementos.MementoPlayer;
import UI.ShipStatus;
import UI.UIComponent;
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
		instance.healthBar = new ShipStatus(new Vector2(100,100), instance.observerHealth(),"bigbar",GameSettings.GetInstance().PlayerData.getHealth());
		UI.UI.getInstance().addUIComponent(instance.healthBar);
	}

	private UIComponent healthBar;

	@Override
	public void onDeath()
	{
		super.onDeath();
		//MatchResult.getInstance().EmpireWins();
	}
	public WeaponSet getBagPack() {
		return weapons;
	}
	private PlayerShip(GameObject referenced) {
		super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteClick(1)));
		//super(referenced,new PlayerBagpack(new DiscreteKeyInput("qQ"), new DiscreteKeyInput(" ")));
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
	}

	public IMementoPlayer makeMemento(){
		return new MementoPlayer(weapons,getPilot());
	}
	public void loadMemento(IMementoPlayer memento){
		getPilot().DestroyComponent();
		setPilot(memento.getPilot());
		weapons.destroy();
		weapons = memento.getWeapons();
		weapons.setActive(true);
		getPilot().setActive(true);
		setData(memento.getData());
	}


	public UIComponent getHealthBar() {
		return healthBar;
	}
}

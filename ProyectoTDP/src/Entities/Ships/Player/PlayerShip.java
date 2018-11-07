package Entities.Ships.Player;

import ADTs.Vector2;
import Engine.GameObject;
import Entities.Ships.Ship;
import Entities.Weapons.PlayerArsenal;
import Entities.Weapons.Arsenal;
import EntitiesVisitor.PlayerVisitor;
import EntitiesVisitor.VisitorEntity;
import GameData.GameSettings;
import Audio.SoundManager;
import InputManager.DiscreteClick;
import InputManager.DiscreteKeyInput;


import SpecialPowers.ISpecialPower;
import SpecialPowers.PowersDeck;
import UI.ArsenalUI;
import UI.ShipStatus;
import UI.UIComponent;
import UtilsBehaviours.PlayerWatcher;

public class PlayerShip extends Ship {


	private UIComponent healthBar;
	private static PlayerShip instance;

	private static ArsenalUI arsenalUI;
	private PowersDeck powerDeck;

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
		var max = GameSettings.GetInstance().PlayerData.clone().getHealth();
		instance.healthBar = new ShipStatus(new Vector2(100,100), instance.observerHealth(),"bigbar",max);
		UI.UI.getInstance().addUIComponent(instance.healthBar);
		arsenalUI = new ArsenalUI(instance.weapons);
		UI.UI.getInstance().addUIComponent(arsenalUI);
	}


	@Override
	public void onDeath()
	{
		UI.UI.getInstance().removeUIComponent(arsenalUI);
		arsenalUI.dispose();
		super.onDeath();
	}

	public Arsenal getBagPack() {
		return weapons;
	}

	private PlayerShip(GameObject referenced) {
		super(referenced,new PlayerArsenal(new DiscreteKeyInput("qQ"), new DiscreteClick(1)));
		//super(referenced,new PlayerArsenal(new DiscreteKeyInput("qQ"), new DiscreteKeyInput("oplOPLkK") ));

		SoundManager.Instance().setTransformListener(this.referenced().transform());
		visitor = new PlayerVisitor();
		data = GameSettings.GetInstance().PlayerData;
		powerDeck = new PowersDeck(new DiscreteKeyInput("Ee "));
	}

	@Override
	public void accept(VisitorEntity visitor) {
		visitor.visit(this);
	}

	public void setLife(float cantLife)
	{
		data.setHealth(cantLife);
	}

	public UIComponent getHealthBar() {
		return healthBar;
	}

	public void addSpecial(ISpecialPower power) {
		this.powerDeck.add(power);
	}
}

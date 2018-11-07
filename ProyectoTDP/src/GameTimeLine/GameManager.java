package GameTimeLine;

import Audio.SoundManager;
import Entities.Ships.Player.PlayerShipDirector;
import Entities.Ships.Player.PlayerShipMaker;

public final class GameManager {

	private static GameManager instance;
	public static GameManager getInstance(){
		instance = (instance==null) ? new GameManager():instance;
		return instance;
	}

	private GameManager(){

	}

	public void StartGame(){
		PlayerShipDirector dir = new PlayerShipDirector();
		dir.setBuilder(new PlayerShipMaker());
		dir.create();
		dir.assemble();
		dir.get();
		SoundManager.Instance().Quote();
		TimeLine.getInstance().playLevel();
	}

}
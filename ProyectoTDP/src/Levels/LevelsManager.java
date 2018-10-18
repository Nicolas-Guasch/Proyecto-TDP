package Levels;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;
import Entities.Ships.PlayerShip;
import GameData.MatchResult;
import SoundSystem.internal.UpdateRunner;

public final class LevelsManager extends Component {

	private static LevelsManager instance;

	public static LevelsManager getInstance(){
		instance = (instance==null) ? new LevelsManager():instance;
		return instance;
	}

	private AbstractLinkedLevel currentLevel;
	private LevelsManager(){
		currentLevel = new Level(1);
		GameObject.getRoot().addChild().addComponent(this);
	}

	@Override
	public void Update() {
		if(!PlayerShip.getInstance().alive()){
			MatchResult.getInstance().EmpireWins();
			setActive(false);
			return;
		}
		if(currentLevel.completed()){
			if(currentLevel.nextLevel() != null){
				currentLevel = currentLevel.nextLevel();
				playLevel();
			}
			else{
				MatchResult.getInstance().AllianceWins();
				setActive(false);
				return;
			}
		}
	}

	public void playLevel() {
		currentLevel.assembleLevel();
		currentLevel.startLevel();
	}
}
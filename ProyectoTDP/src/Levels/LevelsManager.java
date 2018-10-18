package Levels;

import Engine.Component;
import Engine.GameObject;
import Entities.Ships.PlayerShip;
import GameData.MatchResult;

public final class LevelsManager extends Component {

	private static LevelsManager instance;

	public static LevelsManager getInstance(){
		instance = (instance==null) ? new LevelsManager():instance;
		return instance;
	}


	private AbstractLevel[] levels;
	private int currentLevel;
	private LevelsManager(){
		currentLevel =0;
		levels = new AbstractLevel[5];
		levels[0] = new Level(1);
		levels[1] = new Level(2);
		levels[2] = new Level(3);
		levels[3] = new TransitionToBoss();
		levels[4] = new BossLevel();

		GameObject.getRoot().addChild().addComponent(this);
	}

	@Override
	public void Update() {
		if(!PlayerShip.getInstance().alive()){
			MatchResult.getInstance().EmpireWins();
			setActive(false);
			return;
		}

		if(currentLevel().completed()){
			if(hasNextLevel()){
				currentLevel().clean();
				currentLevel++;
				playLevel();
			}
			else{
				MatchResult.getInstance().AllianceWins();
				setActive(false);
			}
		}
	}

	private boolean hasNextLevel(){
		return currentLevel<levels.length-1;
	}
	private AbstractLevel currentLevel() {
		return levels[currentLevel];
	}

	public void playLevel() {
		currentLevel().assembleLevel();
		currentLevel().startLevel();
	}
}
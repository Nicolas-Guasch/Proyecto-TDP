package Levels;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;
import Entities.Ships.PlayerShip;
import GameData.CurrentMatchData;
import GameData.MatchResult;
import UI.UI;

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
		levels = new AbstractLevel[6];
		levels[0] = new Level(1);
		levels[1] = new Level(2);
		levels[2] = new Level(3);
		levels[3] = new TransitionToBoss();
		levels[4] = new BossLevel();
		levels[5] = new BossLevel();

		GameObject.getRoot().addChild().addComponent(this);
	}

	@Override
	public void update() {
		if(!PlayerShip.getInstance().alive()){
			currentLevel().clean();
			MatchResult.getInstance().EmpireWins();
			UI.getInstance().removeUIComponent(PlayerShip.getInstance().getHealthBar());
			setActive(false);
			return;
		}

		if(currentLevel().completed()){
			currentLevel().clean();
			if(hasNextLevel()){
				currentLevel++;
				runTheLevel();
			}
			else{
				MatchResult.getInstance().AllianceWins();
				UI.getInstance().removeUIComponent(PlayerShip.getInstance().getHealthBar());
				setActive(false);
			}
		}
	}

	private boolean hasNextLevel(){
		return currentLevel<levels.length-1;
	}
	public AbstractLevel currentLevel() {
		return levels[currentLevel];
	}

	private void runTheLevel()
	{
		UI.getInstance().startLevel(currentLevel+1);
		currentLevel().assembleLevel();
		currentLevel().startLevel();
		setActive(false);
		EngineGetter.Instance().get().waitForFrames(()->{setActive(true);},5);
	}

	public void playLevel() {
		UI.getInstance().startLevel(0);// intro
		EngineGetter.Instance().get().waitForFrames(this::runTheLevel,500);
	}
}
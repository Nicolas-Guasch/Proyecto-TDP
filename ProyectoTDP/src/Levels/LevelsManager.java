package Levels;

import Engine.Component;
import Engine.EngineGetter;
import Engine.GameObject;
import Entities.Ships.EnemiesBuilders.VaderAMaker;
import Entities.Ships.EnemiesBuilders.VaderBMaker;
import Entities.Ships.EnemiesBuilders.VaderCMaker;
import Entities.Ships.PlayerShip;
import GameData.MatchResult;
import UI.UI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LevelsManager extends Component {

	private static LevelsManager instance;

	public static LevelsManager getInstance(){
		instance = (instance==null) ? new LevelsManager():instance;
		return instance;
	}


	private List<AbstractLevel> levels;
	private int currentLevel;
	private LevelsManager(){
		currentLevel =0;
		levels = new ArrayList<>();


		/*
		* if(number == 3){
            Background.getInstance().setBG("fondo");
            Sun.getInstance().setDaytime(false);
        }
        if(number ==2){
            Background.getInstance().setBG("water");
            Sun.getInstance().setDaytime(true);
        }
        if(number==1){
            Background.getInstance().setBG("fondo_tatooine");
            Sun.getInstance().setDaytime(true);
        */
		float backgroundSpeed = 35;
		AbstractLevel[] _levels = {
				new PlayerAssembler(),
				new TransitionToLevel("bg_space", 1, backgroundSpeed),
				new Level(1),
				new TransitionToLevel("bg_water", 2, backgroundSpeed),
				new Level(2),
				new TransitionToLevel("bg_sand", 3, backgroundSpeed),
				new Level(3),
				new TransitionToLevel("bg_space", 3, backgroundSpeed),
				new TransitionToBoss(),
				new BossLevel(new VaderAMaker()),
				new BossLevel(new VaderBMaker()),
				new BossLevel(new VaderCMaker()),
		};
		levels.addAll(Arrays.asList(_levels));
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
		return currentLevel<levels.size()-1;
	}
	public AbstractLevel currentLevel() {
		return levels.get(currentLevel);
	}

	private void runTheLevel()
	{

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
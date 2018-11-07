package DataParsers;

import GameData.ISettingsParser;
import GameData.FileSettingsParser;

public final class ParsersManager {

	private static ParsersManager instance;
	public static ParsersManager getInstance(){
		instance = (instance==null) ? new ParsersManager():instance;
		return instance;
	}

	private ISettingsParser settingsParser;
	private ILevelsData levelDataParser;

	private ParsersManager(){
		settingsParser = new FileSettingsParser();
		levelDataParser = new LevelsData();
	}

	public ISettingsParser getSettingsParser() {
		return settingsParser;
	}

	public ILevelsData getLevelDataParser() {
		return new LevelsData();
	}
}
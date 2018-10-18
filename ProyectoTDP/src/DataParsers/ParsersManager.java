package DataParsers;

import GameData.ISettingsParser;
import GameData.PlaceHolderSettingsParser;

public final class ParsersManager {

	private static ParsersManager instance;
	public static ParsersManager getInstance(){
		instance = (instance==null) ? new ParsersManager():instance;
		return instance;
	}

	private ISettingsParser settingsParser;
	private ILevelDataParser levelDataParser;

	private ParsersManager(){
		settingsParser = new PlaceHolderSettingsParser();
		levelDataParser = new PlaceHolderLevelDataParser();
	}

	public ISettingsParser getSettingsParser() {
		return settingsParser;
	}

	public ILevelDataParser getLevelDataParser() {
		return levelDataParser;
	}
}
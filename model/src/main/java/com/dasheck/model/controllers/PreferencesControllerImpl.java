package com.dasheck.model.controllers;

import android.content.SharedPreferences;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.utilities.Constants;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class PreferencesControllerImpl implements PreferencesController {

  public static final String GAME_INFORMATION_LIST_NAME_EASY = "gilnEasy";
  public static final String GAME_INFORMATION_LIST_NAME_MEDIUM = "gilnMedium";
  public static final String GAME_INFORMATION_LIST_NAME_HARD = "gilnHard";
  public static final String GAME_INFORMATION_LIST_NAME_EXPERT = "gilnExpert";

  @Inject SharedPreferences sharedPreferences; // TODO: 31/01/16 Use encrypted shared preferences to avoid manipulation
  @Inject GsonController gsonController;

  @Inject public PreferencesControllerImpl() {
  }

  @Override public Observable<Void> addGameInformation(GameInformation gameInformation) {
    return getGameInformationList(gameInformation.getDifficulty()).map(gameInformationList -> {
      gameInformationList.add(gameInformation);
      return gameInformationList;
    }).flatMap(gameInformationList -> saveGameInformationList(gameInformationList, gameInformation.getDifficulty()));
  }

  @Override public Observable<List<GameInformation>> getGameInformationList(int difficulty) {
    return Observable.just(sharedPreferences.getString(getListName(difficulty), "[]"))
        .flatMap(json -> gsonController.listFromJson(json, GameInformation[].class));
  }

  private Observable<Void> saveGameInformationList(List<GameInformation> gameInformationList, int difficulty) {
    return gsonController.listToJson(gameInformationList)
        .map(json -> sharedPreferences.edit().putString(getListName(difficulty), json).commit())
        .map(x -> null);
  }

  private String getListName(int difficulty) {
    switch (difficulty) {
      case Constants.DIFFICULTY_EASY:
        return GAME_INFORMATION_LIST_NAME_EASY;
      case Constants.DIFFICULTY_MEDIUM:
        return GAME_INFORMATION_LIST_NAME_MEDIUM;
      case Constants.DIFFICULTY_HARD:
        return GAME_INFORMATION_LIST_NAME_HARD;
      case Constants.DIFFICULTY_XMETIRX:
        return GAME_INFORMATION_LIST_NAME_EXPERT;
      default:
        throw new IllegalStateException("Game mode is not supported");
    }
  }
}

package com.dasheck.model.controllers;

import android.content.SharedPreferences;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.utilities.Constants;
import com.google.gson.reflect.TypeToken;
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

  @Override public Observable<Void> removeGameInformationList(int difficulty) {
    return Observable.just(sharedPreferences.edit().remove(getListName(difficulty)).commit()).map(x -> null);
  }

  @Override public Observable<Void> writeBoolean(String key, boolean value) {
    return Observable.just(sharedPreferences.edit().putBoolean(key, value).commit()).map(x -> null);
  }

  @Override public Observable<Boolean> readBoolean(String key) {
    return Observable.just(sharedPreferences.getBoolean(key, true));
  }

  @Override public Observable<Void> writeString(String key, String value) {
    return Observable.just(sharedPreferences.edit().putString(key, value).commit()).map(x -> null);
  }

  @Override public Observable<String> readString(String key) {
    return Observable.just(sharedPreferences.getString(key, "Overworld"));
  }

  @Override public Observable<Void> writeFloat(String key, float value) {
    return Observable.just(sharedPreferences.edit().putFloat(key, value).commit()).map(x -> null);
  }

  @Override public Observable<Float> readFloat(String key) {
    return Observable.just(sharedPreferences.getFloat(key, 0.0f));
  }

  @Override public <T> Observable<Void> writeObject(String key, T object) {
    return gsonController.toJson(object).map(json -> sharedPreferences.edit().putString(key, json).commit()).map(x -> null);
  }

  @Override public <T> Observable<T> readObject(String key, Class<T> type) {
    return Observable.just(sharedPreferences.getString(key, "{}")).flatMap(json -> gsonController.fromJson(json, type));
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

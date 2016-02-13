package com.dasheck.model.controllers;

import com.dasheck.model.models.GameInformation;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface PreferencesController {

  Observable<Void> addGameInformation(GameInformation gameInformation);

  Observable<List<GameInformation>> getGameInformationList(int difficulty);

  Observable<Void> removeGameInformationList(int difficulty);

  Observable<Void> writeBoolean(String key, boolean value);

  Observable<Boolean> readBoolean(String key);

  Observable<Void> writeString(String key, String value);

  Observable<String> readString(String key);

  Observable<Void> writeFloat(String key, float value);

  Observable<Float> readFloat(String key);
}

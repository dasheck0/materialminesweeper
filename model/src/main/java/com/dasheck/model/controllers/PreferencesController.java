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
}

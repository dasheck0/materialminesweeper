package com.dasheck.model.datastores;

import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.models.GameInformation;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class StatisticsDatastoreImpl implements StatisticsDatastore {

  @Inject PreferencesController preferencesController;

  @Inject public StatisticsDatastoreImpl() {
  }

  @Override public Observable<GameInformation> getLatestGameInformation(int difficulty) {
    return preferencesController.getGameInformationList(difficulty)
        .map(gameInformationList -> gameInformationList.size() == 0 ? null
            : gameInformationList.get(gameInformationList.size() - 1));
  }

  @Override public Observable<Void> setLatestGameInformation(GameInformation gameInformation) {
    return preferencesController.addGameInformation(gameInformation);
  }
}

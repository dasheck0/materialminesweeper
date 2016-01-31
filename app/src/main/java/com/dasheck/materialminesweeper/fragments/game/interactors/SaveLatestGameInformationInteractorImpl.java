package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.StatisticsDatastore;
import com.dasheck.model.models.GameInformation;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class SaveLatestGameInformationInteractorImpl implements SaveLatestGameInformationInteractor {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public SaveLatestGameInformationInteractorImpl() {
  }

  @Override public Observable<Void> execute(GameInformation gameInformation) {
    return statisticsDatastore.setLatestGameInformation(gameInformation);
  }
}

package com.dasheck.materialminesweeper.fragments.history.interactors;

import com.dasheck.model.datastores.StatisticsDatastore;
import com.dasheck.model.models.GameInformation;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func2;

/**
 * @author Stefan Neidig
 */
public class GetGameInformationListInteractorImpl implements GetGameInformationListInteractor {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public GetGameInformationListInteractorImpl() {
  }

  @Override public Observable<List<GameInformation>> execute() {
    return statisticsDatastore.getGameInformationList()
        .flatMap(Observable::from)
        .toSortedList((gameInformation, gameInformation2) -> -Long.valueOf(gameInformation.getTimestamp())
            .compareTo(gameInformation2.getTimestamp()));
  }
}

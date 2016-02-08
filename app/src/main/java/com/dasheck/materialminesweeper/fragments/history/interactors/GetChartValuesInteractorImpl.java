package com.dasheck.materialminesweeper.fragments.history.interactors;

import com.dasheck.model.datastores.StatisticsDatastore;
import com.dasheck.model.models.ChartValues;
import java.util.Collections;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class GetChartValuesInteractorImpl implements GetChartValuesInteractor {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public GetChartValuesInteractorImpl() {
  }

  @Override public Observable<ChartValues> execute() {
    return statisticsDatastore.getGamesCountAsValueSet()
        .map(gamesCountValueSet -> new ChartValues(Collections.singletonList(gamesCountValueSet)));
  }
}

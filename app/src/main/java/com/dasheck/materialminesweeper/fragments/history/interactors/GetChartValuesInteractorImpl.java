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
    /*
    return Observable.just(new ChartValues());*/
    return Observable.zip(statisticsDatastore.getGamesCountAsValueSet(), statisticsDatastore.getWinningRateAsValueSet(),
        (gamesCount, winningRate) -> {
          ChartValues chartValues = new ChartValues();

          chartValues.getValueSets().put("Games played", gamesCount);
          chartValues.getValueSets().put("Winning rate", winningRate);

          return chartValues;
        });
  }
}

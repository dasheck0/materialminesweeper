package com.dasheck.materialminesweeper.fragments.gamemenu_item.interactors;

import com.dasheck.model.datastores.StatisticsDatastore;
import com.dasheck.model.models.GameStatistics;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class ResetGameStatisticsInteractorImpl implements ResetGameStatisticsInteractor {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public ResetGameStatisticsInteractorImpl() {
  }

  @Override public Observable<GameStatistics> execute(int position) {
    return statisticsDatastore.resetGameStatistics(position);
  }
}

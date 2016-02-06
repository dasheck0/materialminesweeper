package com.dasheck.model.datastores;

import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameStatistics;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface StatisticsDatastore {

  Observable<GameInformation> getLatestGameInformation(int difficulty);

  Observable<GameStatistics> getGameStatistics(int difficulty);

  Observable<GameStatistics> resetGameStatistics(int difficulty);

  Observable<Void> setLatestGameInformation(GameInformation gameInformation);
}

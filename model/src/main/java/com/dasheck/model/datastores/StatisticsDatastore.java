package com.dasheck.model.datastores;

import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameStatistics;
import com.dasheck.model.models.ValueSet;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface StatisticsDatastore {

  Observable<List<GameInformation>> getGameInformationList();

  Observable<GameInformation> getLatestGameInformation(int difficulty);

  Observable<GameStatistics> getGameStatistics(int difficulty);

  Observable<GameStatistics> resetGameStatistics(int difficulty);

  Observable<Void> setLatestGameInformation(GameInformation gameInformation);

  Observable<ValueSet> getWinningRateAsValueSet();

  Observable<ValueSet> getGamesCountAsValueSet();
}

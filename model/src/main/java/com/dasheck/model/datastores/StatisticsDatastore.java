package com.dasheck.model.datastores;

import com.dasheck.model.models.GameInformation;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface StatisticsDatastore {

  Observable<GameInformation> getLatestGameInformation(int difficulty);

  Observable<Void> setLatestGameInformation(GameInformation gameInformation);
}

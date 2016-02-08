package com.dasheck.materialminesweeper.fragments.history.interactors;

import com.dasheck.model.datastores.StatisticsDatastore;
import com.dasheck.model.models.Filter;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.utilities.Constants;
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

  @Override public Observable<List<GameInformation>> execute(Filter filter) {
    return statisticsDatastore.getGameInformationList()
        .flatMap(Observable::from)
        .filter(gameInformation -> ((gameInformation.isWon() && filter.isIncludeWonGames()) || (!gameInformation.isWon()
            && filter.isIncludeLostGames())) && (
            (gameInformation.getDifficulty() == Constants.DIFFICULTY_EASY && filter.isIncludeEasyGames()) ||
                (gameInformation.getDifficulty() == Constants.DIFFICULTY_MEDIUM && filter.isIncludeMediumGames()) ||
                (gameInformation.getDifficulty() == Constants.DIFFICULTY_HARD && filter.isIncludeHardGames()) ||
                (gameInformation.getDifficulty() == Constants.DIFFICULTY_XMETIRX && filter.isIncludeExpertGames())))
        .toSortedList((gameInformation, gameInformation2) -> -Long.valueOf(gameInformation.getTimestamp())
            .compareTo(gameInformation2.getTimestamp()));
  }
}

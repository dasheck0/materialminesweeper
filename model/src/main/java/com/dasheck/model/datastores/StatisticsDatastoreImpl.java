package com.dasheck.model.datastores;

import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameStatistics;
import com.dasheck.model.utilities.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import rx.Observable;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class StatisticsDatastoreImpl implements StatisticsDatastore {

  @Inject PreferencesController preferencesController;

  @Inject public StatisticsDatastoreImpl() {
  }

  @Override public Observable<List<GameInformation>> getGameInformationList() {
    return Observable.zip(preferencesController.getGameInformationList(Constants.DIFFICULTY_EASY),
        preferencesController.getGameInformationList(Constants.DIFFICULTY_MEDIUM),
        preferencesController.getGameInformationList(Constants.DIFFICULTY_HARD),
        preferencesController.getGameInformationList(Constants.DIFFICULTY_XMETIRX), (easy, medium, hard, expert) -> {
          List<GameInformation> result = new ArrayList<>();

          result.addAll(easy);
          result.addAll(medium);
          result.addAll(hard);
          result.addAll(expert);

          return result;
        });
  }

  @Override public Observable<GameInformation> getLatestGameInformation(int difficulty) {
    return preferencesController.getGameInformationList(difficulty)
        .map(gameInformationList -> gameInformationList.size() == 0 ? null
            : gameInformationList.get(gameInformationList.size() - 1));
  }

  @Override public Observable<GameStatistics> getGameStatistics(int difficulty) {
    return preferencesController.getGameInformationList(difficulty)
        .flatMap(gameInformationList -> Observable.zip(getGameCount(gameInformationList),
            getTotalTimeSpent(gameInformationList), getAverageTimeSpent(gameInformationList),
            getLongestStreak(gameInformationList), getWinningRate(gameInformationList), GameStatistics::new));
  }

  @Override public Observable<GameStatistics> resetGameStatistics(int difficulty) {
    return preferencesController.removeGameInformationList(difficulty).flatMap(x -> getGameStatistics(difficulty));
  }

  @Override public Observable<Void> setLatestGameInformation(GameInformation gameInformation) {
    return preferencesController.addGameInformation(gameInformation);
  }

  private Observable<Integer> getGameCount(List<GameInformation> gameInformationList) {
    return Observable.just(gameInformationList.size());
  }

  private Observable<Long> getTotalTimeSpent(List<GameInformation> gameInformationList) {
    return Observable.from(gameInformationList)
        .map(GameInformation::getElapsedTime)
        .reduce(0L, (first, second) -> first + second);
  }

  private Observable<Long> getAverageTimeSpent(List<GameInformation> gameInformationList) {
    return Observable.zip(getGameCount(gameInformationList), getTotalTimeSpent(gameInformationList),
        (gameCount, totalTime) -> (long) totalTime / (gameCount == 0 ? 1 : gameCount));
  }

  private Observable<Integer> getLongestStreak(List<GameInformation> gameInformationList) {
    //return Observable.from(gameInformationList).
    return Observable.just(0);
  }

  private Observable<Float> getWinningRate(List<GameInformation> gameInformationList) {
    return Observable.from(gameInformationList)
        .filter(GameInformation::isWon)
        .count()
        .map(gamesWon -> (float) gamesWon / (gameInformationList.size() == 0 ? 1 : gameInformationList.size()));
  }
}

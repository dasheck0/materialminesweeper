package com.dasheck.model.datastores;

import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameStatistics;
import com.dasheck.model.models.ValueSet;
import com.dasheck.model.utilities.Constants;
import com.dasheck.model.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func2;
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

  @Override public Observable<ValueSet> getWinningRateAsValueSet() {
    return getGameInformationList().flatMap(Observable::from)
        .toSortedList((gameInformation, gameInformation2) -> Long.valueOf(gameInformation.getTimestamp())
            .compareTo(gameInformation2.getTimestamp()))
        .map(list -> {
          List<String> keys = new ArrayList<>();
          List<Float> values = new ArrayList<>();
          int itemCount = 0;
          int wonCount = 0;
          String currentKey = "";

          for (int i = 0; i < list.size(); i++) {
            GameInformation gameInformation = list.get(i);
            String key = Utilities.timestampToReadble(gameInformation.getTimestamp(), "dd/MM/yyyy");

            if (!key.equalsIgnoreCase(currentKey)) {
              keys.add(currentKey);
              values.add((float) wonCount / (itemCount == 0 ? 1 : itemCount) * 100.0f);
              currentKey = key;

              itemCount = 0;
              wonCount = 0;
            }

            itemCount += 1;
            wonCount += gameInformation.isWon() ? 1 : 0;

            if (i == list.size() - 1) {
              keys.add(key);
              values.add((float) wonCount / (itemCount == 0 ? 1 : itemCount) * 100.0f);
            }
          }

          return new ValueSet(keys, values, 0.0f, 100.0f);
        });
  }

  @Override public Observable<ValueSet> getGamesCountAsValueSet() {
    return getGameInformationList().flatMap(Observable::from)
        .toSortedList((gameInformation, gameInformation2) -> Long.valueOf(gameInformation.getTimestamp())
            .compareTo(gameInformation2.getTimestamp()))
        .map(list -> {
          List<String> keys = new ArrayList<>();
          List<Float> values = new ArrayList<>();
          int itemCount = 0;
          int maxItemCount = 0;
          String currentKey = "";

          for (int i = 0; i < list.size(); i++) {
            GameInformation gameInformation = list.get(i);
            String key = Utilities.timestampToReadble(gameInformation.getTimestamp(), "dd/MM/yyyy");

            if (!key.equalsIgnoreCase(currentKey)) {
              keys.add(currentKey);
              values.add((float) itemCount);
              currentKey = key;

              itemCount = 0;
            }

            itemCount += 1;

            if (itemCount > maxItemCount) {
              maxItemCount = itemCount;
            }

            if (i == list.size() - 1) {
              keys.add(key);
              values.add((float) itemCount);
            }
          }

          return new ValueSet(keys, values, 0.0f, maxItemCount + 10);
        });
  }

  @Override public Observable<ValueSet> getAverageTimePlayedAsValueSet() {
    return getGameInformationList().flatMap(Observable::from)
        .toSortedList((gameInformation, gameInformation2) -> Long.valueOf(gameInformation.getTimestamp())
            .compareTo(gameInformation2.getTimestamp()))
        .map(list -> {
          List<String> keys = new ArrayList<>();
          List<Float> values = new ArrayList<>();
          List<Long> elapsed = new ArrayList<>();
          float maxElapsed = 0.0f;
          String currentKey = "";

          for (int i = 0; i < list.size(); i++) {
            GameInformation gameInformation = list.get(i);
            String key = Utilities.timestampToReadble(gameInformation.getTimestamp(), "dd/MM/yyyy");

            if (!key.equalsIgnoreCase(currentKey)) {
              keys.add(currentKey);
              long totalElapsedTime = 0;

              for (Long aLong : elapsed) {
                totalElapsedTime += aLong;
              }

              float averageTime = (float) (totalElapsedTime / (elapsed.size() == 0 ? 1 : elapsed.size()));
              if (averageTime > maxElapsed) {
                maxElapsed = averageTime;
              }

              values.add(averageTime);
              currentKey = key;

              elapsed.clear();
            }

            elapsed.add(gameInformation.getElapsedTime());

            if (i == list.size() - 1) {
              keys.add(key);
              long totalElapsedTime = 0;

              for (Long aLong : elapsed) {
                totalElapsedTime += aLong;
              }

              float averageTime = (float) (totalElapsedTime / (elapsed.size() == 0 ? 1 : elapsed.size()));
              if (averageTime > maxElapsed) {
                maxElapsed = averageTime;
              }

              values.add(averageTime);
              currentKey = key;
            }
          }

          return new ValueSet(keys, values, 0.0f, maxElapsed);
        });
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

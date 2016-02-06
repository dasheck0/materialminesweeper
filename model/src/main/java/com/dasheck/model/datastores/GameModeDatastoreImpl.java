package com.dasheck.model.datastores;

import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.models.GameStatistics;
import com.dasheck.model.utilities.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class GameModeDatastoreImpl implements GameModeDatastore {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public GameModeDatastoreImpl() {
  }

  // TODO: 31/01/16 Load this from a file -> assets
  @Override public Observable<List<GameMode>> get() {
    return Observable.zip(getGameModeNames(), getGameConfigurations(), getGameInformation(), getGameStatistics(),
        (names, configurations, information, statistices) -> {
          List<GameMode> modes = new ArrayList<>();

          for (int i = 0; i < names.size(); i++) {
            modes.add(new GameMode(names.get(i), configurations.get(i), information.get(i), statistices.get(i)));
          }

          return modes;
        });
  }

  private Observable<List<String>> getGameModeNames() {
    return Observable.create(new Observable.OnSubscribe<List<String>>() {
      @Override public void call(Subscriber<? super List<String>> subscriber) {
        List<String> result = new ArrayList<String>();

        result.add("Easy");
        result.add("Medium");
        result.add("Hard");
        result.add("Expert");

        subscriber.onNext(result);
        subscriber.onCompleted();
      }
    });
  }

  private Observable<List<Configuration>> getGameConfigurations() {
    return Observable.create(new Observable.OnSubscribe<List<Configuration>>() {
      @Override public void call(Subscriber<? super List<Configuration>> subscriber) {
        List<Configuration> result = new ArrayList<Configuration>();

        result.add(new Configuration(9, 9, 10, Constants.DIFFICULTY_EASY));
        result.add(new Configuration(16, 16, 40, Constants.DIFFICULTY_MEDIUM));
        result.add(new Configuration(16, 30, 99, Constants.DIFFICULTY_HARD));
        result.add(new Configuration(25, 25, 150, Constants.DIFFICULTY_XMETIRX));

        subscriber.onNext(result);
        subscriber.onCompleted();
      }
    });
  }

  private Observable<List<GameInformation>> getGameInformation() {
    return getDifficulties().flatMap(Observable::from)
        .flatMap(statisticsDatastore::getLatestGameInformation)
        .toList()
        .map(list -> list == null ? new ArrayList<>() : list);
  }

  private Observable<List<GameStatistics>> getGameStatistics() {
    return getDifficulties().flatMap(Observable::from).flatMap(statisticsDatastore::getGameStatistics).toList();
  }

  private Observable<List<Integer>> getDifficulties() {
    return Observable.create(new Observable.OnSubscribe<List<Integer>>() {
      @Override public void call(Subscriber<? super List<Integer>> subscriber) {
        List<Integer> result = new ArrayList<Integer>();

        result.add(Constants.DIFFICULTY_EASY);
        result.add(Constants.DIFFICULTY_MEDIUM);
        result.add(Constants.DIFFICULTY_HARD);
        result.add(Constants.DIFFICULTY_XMETIRX);

        subscriber.onNext(result);
        subscriber.onCompleted();
      }
    });
  }
}

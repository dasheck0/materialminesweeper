package com.dasheck.model.datastores;

import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * @author Stefan Neidig
 */
public class GameModeDatastoreImpl implements GameModeDatastore {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public GameModeDatastoreImpl() {
  }

  // TODO: 31/01/16 Load this from a file -> assets
  @Override public Observable<List<GameMode>> get() {
    return Observable.zip(statisticsDatastore.getLatestGameInformation(FieldDatastore.DIFFICULTY_EASY),
        statisticsDatastore.getLatestGameInformation(FieldDatastore.DIFFICULTY_MEDIUM),
        statisticsDatastore.getLatestGameInformation(FieldDatastore.DIFFICULTY_HARD),
        statisticsDatastore.getLatestGameInformation(FieldDatastore.DIFFICULTY_XMETIRX),
        (easy, medium, hard, expert) -> {
          List<GameMode> modes = new ArrayList<>();

          List<GameInformation> easyList = easy == null ? new ArrayList<>() : Arrays.asList(easy);
          List<GameInformation> mediumList = medium == null ? new ArrayList<>() : Arrays.asList(medium);
          List<GameInformation> hardList = hard == null ? new ArrayList<>() : Arrays.asList(hard);
          List<GameInformation> expertList = expert == null ? new ArrayList<>() : Arrays.asList(expert);

          modes.add(
              new GameMode("Easy", Arrays.asList(new Configuration(10, 10, FieldDatastore.DIFFICULTY_EASY)), easyList));
          modes.add(new GameMode("Medium", Arrays.asList(new Configuration(20, 20, FieldDatastore.DIFFICULTY_MEDIUM)),
              mediumList));
          modes.add(
              new GameMode("Hard", Arrays.asList(new Configuration(30, 30, FieldDatastore.DIFFICULTY_HARD)), hardList));
          modes.add(new GameMode("XMetriX", Arrays.asList(new Configuration(40, 40, FieldDatastore.DIFFICULTY_XMETIRX)),
              expertList));

          return modes;
        });
  }
}

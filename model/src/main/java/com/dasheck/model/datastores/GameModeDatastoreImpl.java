package com.dasheck.model.datastores;

import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.utilities.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class GameModeDatastoreImpl implements GameModeDatastore {

  @Inject StatisticsDatastore statisticsDatastore;

  @Inject public GameModeDatastoreImpl() {
  }

  // TODO: 31/01/16 Load this from a file -> assets
  @Override public Observable<List<GameMode>> get() {
    return Observable.zip(statisticsDatastore.getLatestGameInformation(Constants.DIFFICULTY_EASY),
        statisticsDatastore.getLatestGameInformation(Constants.DIFFICULTY_MEDIUM),
        statisticsDatastore.getLatestGameInformation(Constants.DIFFICULTY_HARD),
        statisticsDatastore.getLatestGameInformation(Constants.DIFFICULTY_XMETIRX),
        (easy, medium, hard, expert) -> {
          List<GameMode> modes = new ArrayList<>();

          List<GameInformation> easyList = easy == null ? new ArrayList<>() : Arrays.asList(easy);
          List<GameInformation> mediumList = medium == null ? new ArrayList<>() : Arrays.asList(medium);
          List<GameInformation> hardList = hard == null ? new ArrayList<>() : Arrays.asList(hard);
          List<GameInformation> expertList = expert == null ? new ArrayList<>() : Arrays.asList(expert);

          modes.add(new GameMode("Easy", Arrays.asList(new Configuration(8, 8, 10, Constants.DIFFICULTY_EASY)),
              easyList));
          modes.add(
              new GameMode("Medium", Arrays.asList(new Configuration(16, 16, 40, Constants.DIFFICULTY_MEDIUM)),
                  mediumList));
          modes.add(new GameMode("Hard", Arrays.asList(new Configuration(16, 30, 99, Constants.DIFFICULTY_HARD)),
              hardList));
          modes.add(
              new GameMode("XMetriX", Arrays.asList(new Configuration(25, 25, 150, Constants.DIFFICULTY_XMETIRX)),
                  expertList));

          return modes;
        });
  }
}

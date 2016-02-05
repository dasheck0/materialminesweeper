package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.datastores.GameModeDatastore;
import com.dasheck.model.datastores.GameModeDatastoreImpl;
import com.dasheck.model.datastores.StatisticsDatastore;
import com.dasheck.model.datastores.StatisticsDatastoreImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 23/01/16.
 */
@Module public class DatastoresModule {

  @Provides @PerActivity
  public GameModeDatastore provideGameModeDatastore(GameModeDatastoreImpl gameModeDatastoreImpl) {
    return gameModeDatastoreImpl;
  }

  @Provides @PerActivity
  public StatisticsDatastore provideStatisticsDatastore(StatisticsDatastoreImpl statisticsDatastoreImpl) {
    return statisticsDatastoreImpl;
  }
}

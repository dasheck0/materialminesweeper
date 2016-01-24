package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.datastores.FieldDatastoreImpl;
import com.dasheck.model.models.Field;
import dagger.Module;
import dagger.Provides;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by s.neidig on 23/01/16.
 */
@Module public class DatastoresModule {

  @Provides @PerActivity FieldDatastore provideFieldDatastore(FieldDatastoreImpl fieldDatastore) {
    return fieldDatastore;
  }
}

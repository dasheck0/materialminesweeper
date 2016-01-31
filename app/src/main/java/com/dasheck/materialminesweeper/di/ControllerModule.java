package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.controllers.GameTimeController;
import com.dasheck.model.controllers.GameTimeControllerImpl;
import com.dasheck.model.controllers.GsonController;
import com.dasheck.model.controllers.GsonControllerImpl;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.controllers.PreferencesControllerImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 24/01/16.
 */
@Module public class ControllerModule {

  @Provides @PerActivity GameTimeController provideGameTimeController(GameTimeControllerImpl gameTimeController) {
    return gameTimeController;
  }

  @Provides @PerActivity public GsonController provideGsonController(GsonControllerImpl gsonControllerImpl) {
    return gsonControllerImpl;
  }

  @Provides @PerActivity
  public PreferencesController preferencesController(PreferencesControllerImpl preferencesControllerImpl) {
    return preferencesControllerImpl;
  }
}

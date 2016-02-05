package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.controllers.CurrentGameControllerImpl;
import com.dasheck.model.controllers.GameTimeController;
import com.dasheck.model.controllers.GameTimeControllerImpl;
import com.dasheck.model.controllers.GsonController;
import com.dasheck.model.controllers.GsonControllerImpl;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.controllers.PreferencesControllerImpl;
import com.dasheck.model.controllers.field_controllers.FieldController;
import com.dasheck.model.controllers.field_controllers.SimpleFieldControllerImpl;
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

  @Provides @PerActivity public FieldController provideFieldController(SimpleFieldControllerImpl fieldControllerImpl) {
    return fieldControllerImpl;
  }

  @Provides @PerActivity
  public CurrentGameController provideCurrentGameController(CurrentGameControllerImpl currentGameControllerImpl) {
    return currentGameControllerImpl;
  }
}

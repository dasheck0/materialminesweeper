package com.dasheck.materialminesweeper.di;

import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.model.controllers.GameTimeController;
import com.dasheck.model.controllers.GameTimeControllerImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by s.neidig on 24/01/16.
 */
@Module public class ControllerModule {

  @Provides @PerActivity GameTimeController provideGameTimeController(
      GameTimeControllerImpl gameTimeController) {
    return gameTimeController;
  }
}

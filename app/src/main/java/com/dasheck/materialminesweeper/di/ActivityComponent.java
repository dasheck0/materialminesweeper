package com.dasheck.materialminesweeper.di;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import com.dasheck.materialminesweeper.activities.BaseActivity;
import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.materialminesweeper.annotations.PerActivity;
import com.dasheck.materialminesweeper.controllers.ShareController;
import com.dasheck.materialminesweeper.controllers.VibrationController;
import com.dasheck.materialminesweeper.controllers.WebController;
import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.controllers.GameTimeController;
import com.dasheck.model.controllers.GsonController;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.controllers.field_controllers.FieldController;
import com.dasheck.model.datastores.GameModeDatastore;
import com.dasheck.model.datastores.SettingsDatastore;
import com.dasheck.model.datastores.StatisticsDatastore;
import dagger.Component;

/**
 * Created by s.neidig on 17/01/16.
 */
@PerActivity @Component(modules = {
    AcitivityModule.class, TransformerModule.class, DatastoresModule.class, ControllerModule.class
}, dependencies = ApplicationComponent.class) public interface ActivityComponent {

  BaseActivity baseActivity();

  Context context();

  Navigator navigator();

  FragmentManager supportFragmentManager();

  /* Transformer */

  /* Datastores */

  GameModeDatastore gameModeDatastore();

  StatisticsDatastore statisticsDatastore();

  SettingsDatastore settingsDatastore();

  /* Controllers */

  GameTimeController gameTimeController();

  GsonController gsonController();

  PreferencesController preferencesController();

  FieldController fieldController();

  CurrentGameController currentGameController();

  VibrationController vibrationController();

  WebController webController();

  ShareController shareController();
}

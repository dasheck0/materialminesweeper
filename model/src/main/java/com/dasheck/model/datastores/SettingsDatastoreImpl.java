package com.dasheck.model.datastores;

import com.dasheck.model.controllers.PreferencesController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class SettingsDatastoreImpl implements SettingsDatastore {

  private static final String SETTINGS_VIBRATION = "settingsVibration";

  @Inject PreferencesController preferencesController;

  @Inject public SettingsDatastoreImpl() {
  }

  @Override public Observable<Boolean> isVibrationEnabled() {
    return preferencesController.readBoolean(SETTINGS_VIBRATION);
  }

  @Override public Observable<Void> enableVibration(boolean enabled) {
    return preferencesController.writeBoolean(SETTINGS_VIBRATION, enabled);
  }
}

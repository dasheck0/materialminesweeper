package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.model.datastores.SettingsDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class IsVibrationEnabledInteractorImpl implements IsVibrationEnabledInteractor {

  @Inject SettingsDatastore settingsDatastore;

  @Inject public IsVibrationEnabledInteractorImpl() {
  }

  @Override public Observable<Boolean> execute() {
    return settingsDatastore.isVibrationEnabled();
  }
}

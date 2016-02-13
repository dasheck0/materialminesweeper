package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.model.datastores.SettingsDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class IsSoundEnabledInteractorImpl implements IsSoundEnabledInteractor {

  @Inject SettingsDatastore settingsDatastore;

  @Inject public IsSoundEnabledInteractorImpl() {
  }

  @Override public Observable<Boolean> execute() {
    return settingsDatastore.isSoundEnabled();
  }
}

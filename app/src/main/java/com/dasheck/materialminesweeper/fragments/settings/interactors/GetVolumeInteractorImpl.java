package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.model.datastores.SettingsDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class GetVolumeInteractorImpl implements GetVolumeInteractor {

  @Inject SettingsDatastore settingsDatastore;

  @Inject public GetVolumeInteractorImpl() {
  }

  @Override public Observable<Float> execute() {
    return settingsDatastore.getVolume();
  }
}

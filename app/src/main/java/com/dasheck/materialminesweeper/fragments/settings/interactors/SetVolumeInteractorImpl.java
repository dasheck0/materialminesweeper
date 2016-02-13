package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.materialminesweeper.controllers.SoundController;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.datastores.SettingsDatastore;
import java.util.prefs.PreferenceChangeEvent;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class SetVolumeInteractorImpl implements SetVolumeInteractor {

  @Inject SettingsDatastore settingsDatastore;
  @Inject SoundController soundController;

  @Inject public SetVolumeInteractorImpl() {
  }

  @Override public Observable<Void> execute(float volume) {
    return Observable.zip(settingsDatastore.setVolume(volume), soundController.setVolume(volume),
        (first, second) -> null);
  }
}

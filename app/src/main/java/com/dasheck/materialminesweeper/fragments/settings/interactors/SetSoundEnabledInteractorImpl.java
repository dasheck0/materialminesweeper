package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.materialminesweeper.controllers.SoundController;
import com.dasheck.materialminesweeper.fragments.menu.interactors.SetupSoundEffectsInteractor;
import com.dasheck.model.datastores.SettingsDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class SetSoundEnabledInteractorImpl implements SetSoundEnabledInteractor {

  @Inject SettingsDatastore settingsDatastore;
  @Inject SoundController soundController;

  @Inject public SetSoundEnabledInteractorImpl() {
  }

  @Override public Observable<Void> execute(boolean enabled) {
    return settingsDatastore.enableSound(enabled).flatMap(x -> {
      if (enabled) {
        return soundController.startBackgroundMusic();
      } else {
        return soundController.stopBackgroundMusic();
      }
    });
  }
}

package com.dasheck.materialminesweeper.fragments.settings.interactors;

import android.content.Context;
import com.dasheck.materialminesweeper.controllers.SoundController;
import com.dasheck.model.datastores.SettingsDatastore;
import com.dasheck.model.models.BackgroundMusic;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class SelectBackgroundMusicInteractorImpl implements SelectBackgroundMusicInteractor {

  @Inject SettingsDatastore settingsDatastore;
  @Inject SoundController soundController;
  @Inject Context context;

  @Inject public SelectBackgroundMusicInteractorImpl() {
  }

  @Override public Observable<Void> execute(BackgroundMusic backgroundMusic) {
    return settingsDatastore.selectBackgroundMusic(backgroundMusic.getTitle()).flatMap(x -> {
      int resourceId =
          context.getResources().getIdentifier(backgroundMusic.getResourceName(), "raw", context.getPackageName());
      return soundController.registerBackgroundMusic(resourceId).flatMap(y -> soundController.startBackgroundMusic());
    });
  }
}

package com.dasheck.materialminesweeper.fragments.menu.interactors;

import android.content.Context;
import com.dasheck.materialminesweeper.controllers.SoundController;
import com.dasheck.model.controllers.PreferencesController;
import com.dasheck.model.datastores.SettingsDatastore;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

import static android.R.attr.x;

/**
 * @author Stefan Neidig
 */
public class SetupSoundEffectsInteractorImpl implements SetupSoundEffectsInteractor {

  @Inject SettingsDatastore settingsDatastore;
  @Inject SoundController soundController;
  @Inject Context context;

  @Inject public SetupSoundEffectsInteractorImpl() {
  }

  @Override public Observable<Void> execute() {
    return soundController.isBackgroundMusicRegistered().flatMap(hasBackgroundMusic -> {
      if (!hasBackgroundMusic) {
        return setSelectedBackgroundMusic();
      } else {
        return nothing();
      }
    }).flatMap(x -> setVolume());
  }

  private Observable<Void> setSelectedBackgroundMusic() {
    return settingsDatastore.getSelectedBackgroundMusic().flatMap(backgroundMusic -> {
      if (backgroundMusic != null) {
        int resourceId =
            context.getResources().getIdentifier(backgroundMusic.getResourceName(), "raw", context.getPackageName());

        return soundController.registerBackgroundMusic(resourceId).flatMap(x -> soundController.startBackgroundMusic());
      }

      return nothing();
    });
  }

  private Observable<Void> setVolume() {
    return settingsDatastore.getVolume().flatMap(soundController::setVolume);
  }

  private Observable<Void> nothing() {
    return Observable.create(new Observable.OnSubscribe<Void>() {
      @Override public void call(Subscriber<? super Void> subscriber) {
        subscriber.onNext(null);
        subscriber.onCompleted();
      }
    });
  }
}

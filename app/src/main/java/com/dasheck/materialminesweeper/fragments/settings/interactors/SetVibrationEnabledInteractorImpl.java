package com.dasheck.materialminesweeper.fragments.settings.interactors;

import com.dasheck.materialminesweeper.controllers.VibrationController;
import com.dasheck.model.datastores.SettingsDatastore;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class SetVibrationEnabledInteractorImpl implements SetVibrationEnabledInteractor {

  @Inject SettingsDatastore settingsDatastore;
  @Inject VibrationController vibrationController;

  @Inject public SetVibrationEnabledInteractorImpl() {
  }

  @Override public Observable<Void> execute(boolean enabled) {
    return settingsDatastore.enableVibration(enabled)
        .flatMap(x -> enabled ? vibrationController.vibrate(VibrationController.VIBRATION_SHORT)
            : Observable.create(new Observable.OnSubscribe<Void>() {
              @Override public void call(Subscriber<? super Void> subscriber) {
                subscriber.onNext(null);
                subscriber.onCompleted();
              }
            }));
  }
}

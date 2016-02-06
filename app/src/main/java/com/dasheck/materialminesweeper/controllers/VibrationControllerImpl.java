package com.dasheck.materialminesweeper.controllers;

import android.content.Context;
import android.os.Vibrator;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class VibrationControllerImpl implements VibrationController {

  private Vibrator vibrator;

  @Inject public VibrationControllerImpl(Context context) {
    this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
  }

  @Override public Observable<Void> vibrate(long timeInMillis) {
    if (vibrator != null && vibrator.hasVibrator()) {
      vibrator.vibrate(timeInMillis);
    }

    return Observable.just(null);
  }

  @Override public Observable<Void> cancel() {
    if (vibrator != null && vibrator.hasVibrator()) {
      vibrator.cancel();
    }

    return Observable.just(null);
  }
}

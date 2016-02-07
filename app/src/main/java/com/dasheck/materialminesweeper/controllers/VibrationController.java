package com.dasheck.materialminesweeper.controllers;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface VibrationController {

  public static final int VIBRATION_SHORT = 150;

  Observable<Boolean> supportsVibration();

  Observable<Void> vibrate(long timeInMillis);

  Observable<Void> cancel();
}

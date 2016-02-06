package com.dasheck.materialminesweeper.controllers;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface VibrationController {

  Observable<Void> vibrate(long timeInMillis);

  Observable<Void> cancel();
}

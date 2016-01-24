package com.dasheck.model.controllers;

import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public interface GameTimeController {

  Observable<Void> reset();

  Observable<Long> getElapsed();

  Observable<Void> set(long timestamp);
}

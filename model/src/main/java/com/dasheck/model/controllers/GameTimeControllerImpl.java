package com.dasheck.model.controllers;

import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GameTimeControllerImpl implements GameTimeController {

  private long elapsed;
  private long currentStartTime;

  @Inject public GameTimeControllerImpl() {
    elapsed = 0;
  }

  @Override public Observable<Void> reset() {
    elapsed = 0;
    currentStartTime = getCurrentTime();
    return Observable.just(null);
  }

  @Override public Observable<Long> getElapsed() {
    return Observable.just(elapsed + getCurrentTime() - currentStartTime);
  }

  @Override public Observable<Void> set(long timestamp) {
    currentStartTime = timestamp;
    return Observable.just(null);
  }

  private long getCurrentTime() {
    return System.currentTimeMillis() / 1000;
  }
}

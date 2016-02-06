package com.dasheck.model.controllers;

import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.time.StopWatch;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GameTimeControllerImpl implements GameTimeController {

  private static final int STATE_RUNNING = 0;

  private static final int STATE_STOPPED = 1;

  private static final int STATE_PAUSED = 2;

  private StopWatch stopWatch;
  private int stopWatchState;

  @Inject public GameTimeControllerImpl() {
    this.stopWatch = new StopWatch();
  }

  @Override public Observable<Void> start() {
    stopWatch.start();
    stopWatchState = STATE_RUNNING;
    return Observable.just(null);
  }

  @Override public Observable<Void> stop() {
    stopWatch.stop();
    stopWatchState = STATE_STOPPED;
    return Observable.just(null);
  }

  @Override public Observable<Void> reset() {
    stopWatch.reset();
    return Observable.just(null);
  }

  @Override public Observable<Long> getElapsed() {
    return Observable.just(stopWatch.getTime() / 1000L);
  }

  @Override public Observable<Void> set(long timestamp) {
    throw new NotImplementedException("Currently not implemented");
  }

  @Override public Observable<Void> pause() {
    if (stopWatchState == STATE_RUNNING) {
      stopWatch.suspend();
      stopWatchState = STATE_PAUSED;
    }

    return Observable.just(null);
  }

  @Override public Observable<Void> unpause() {
    if (stopWatchState == STATE_PAUSED) {
      stopWatch.resume();
      stopWatchState = STATE_RUNNING;
    }

    return Observable.just(null);
  }
}

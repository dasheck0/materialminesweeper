package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.GameTimeController;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GetElapsedTimeInteractorImpl implements GetElapsedTimeInteractor {

  @Inject GameTimeController gameTimeController;

  @Inject public GetElapsedTimeInteractorImpl() {
  }

  @Override public Observable<Long> execute() {
    return gameTimeController.getElapsed();
  }
}

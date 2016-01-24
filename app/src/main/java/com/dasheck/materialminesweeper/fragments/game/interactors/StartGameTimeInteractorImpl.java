package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.GameTimeController;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class StartGameTimeInteractorImpl implements StartGameTimeInteractor {

  @Inject GameTimeController gameTimeController;

  @Inject public StartGameTimeInteractorImpl() {
  }

  @Override public Observable<Void> execute() {
    return gameTimeController.reset();
  }
}

package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.GameTimeController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class PauseGameInteractorImpl implements PauseGameInteractor {

  @Inject GameTimeController gameTimeController;

  @Inject public PauseGameInteractorImpl() {
  }

  @Override public Observable<Void> execute(boolean pause) {
    return pause ? gameTimeController.pause() : gameTimeController.unpause();
  }
}

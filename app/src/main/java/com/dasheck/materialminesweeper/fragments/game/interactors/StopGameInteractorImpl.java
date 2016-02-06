package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class StopGameInteractorImpl implements StopGameInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public StopGameInteractorImpl() {
  }

  @Override public Observable<Void> execute() {
    return currentGameController.stopGame();
  }
}

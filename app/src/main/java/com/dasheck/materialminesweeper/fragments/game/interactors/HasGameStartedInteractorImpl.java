package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class HasGameStartedInteractorImpl implements HasGameStartedInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public HasGameStartedInteractorImpl() {
  }

  @Override public Observable<Boolean> execute() {
    return currentGameController.hasGameStarted();
  }
}

package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class IsGameWonInteractorImpl implements IsGameWonInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public IsGameWonInteractorImpl() {
  }

  @Override public Observable<Boolean> execute() {
    return currentGameController.isGameWon();
  }
}

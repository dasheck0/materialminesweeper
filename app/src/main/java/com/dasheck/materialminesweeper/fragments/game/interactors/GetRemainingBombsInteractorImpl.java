package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GetRemainingBombsInteractorImpl implements GetRemainingBombsInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public GetRemainingBombsInteractorImpl() {
  }

  @Override public Observable<Integer> execute() {
    return currentGameController.getNumberOfRemainingBombs();
  }
}

package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.models.Position;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class IsTileRevealedInteractorImpl implements IsTileRevealedInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public IsTileRevealedInteractorImpl() {
  }

  @Override public Observable<Boolean> execute(Position position) {
    return currentGameController.isTileRevealed(position);
  }
}

package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.models.Position;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class QuickRevealTileInteractorImpl implements QuickRevealTileInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public QuickRevealTileInteractorImpl() {
  }

  @Override public Observable<Boolean> execute(Position position) {
    return currentGameController.quickRevealTile(position);
  }
}

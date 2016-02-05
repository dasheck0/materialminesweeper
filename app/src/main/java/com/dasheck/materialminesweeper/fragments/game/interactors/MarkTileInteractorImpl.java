package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.models.Tile;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public class MarkTileInteractorImpl implements MarkTileInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public MarkTileInteractorImpl() {
  }

  @Override public Observable<Void> execute(Tile tile) {
    return currentGameController.markTile(tile.getPosition());
  }
}

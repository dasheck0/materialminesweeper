package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Tile;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public class RevealTileInteractorImpl implements RevealTileInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public RevealTileInteractorImpl() {
  }

  @Override public Observable<Boolean> execute(Tile tile) {
    return fieldDatastore.revealTile(tile.getPosition());
  }
}

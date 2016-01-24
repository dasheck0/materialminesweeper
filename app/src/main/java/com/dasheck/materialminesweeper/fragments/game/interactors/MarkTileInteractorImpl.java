package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Tile;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public class MarkTileInteractorImpl implements MarkTileInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public MarkTileInteractorImpl() {
  }

  @Override public Observable<Void> execute(Tile tile) {
    return fieldDatastore.markTile(tile.getPosition());
  }
}

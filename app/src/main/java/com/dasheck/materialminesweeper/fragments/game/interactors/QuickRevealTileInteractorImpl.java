package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Position;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class QuickRevealTileInteractorImpl implements QuickRevealTileInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public QuickRevealTileInteractorImpl() {
  }

  @Override public Observable<Boolean> execute(Position position) {
    return fieldDatastore.quickRevealTile(position);
  }
}

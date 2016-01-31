package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Position;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class IsTileRevealedInteractorImpl implements IsTileRevealedInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public IsTileRevealedInteractorImpl() {
  }

  @Override public Observable<Boolean> execute(Position position) {
    return fieldDatastore.isTileRevealed(position);
  }
}

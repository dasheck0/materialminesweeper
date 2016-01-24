package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.FieldDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GetRemainingBombsInteractorImpl implements GetRemainingBombsInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public GetRemainingBombsInteractorImpl() {
  }

  @Override public Observable<Integer> execute() {
    return fieldDatastore.getNumberOfRemainingBombs();
  }
}

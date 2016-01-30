package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.datastores.FieldDatastore;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class IsGameWonInteractorImpl implements IsGameWonInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public IsGameWonInteractorImpl() {
  }

  @Override public Observable<Boolean> execute() {
    return fieldDatastore.isGameWon();
  }
}

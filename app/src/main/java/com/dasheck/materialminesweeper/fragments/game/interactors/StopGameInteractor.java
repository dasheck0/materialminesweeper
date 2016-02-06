package com.dasheck.materialminesweeper.fragments.game.interactors;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface StopGameInteractor {

  Observable<Void> execute();
}

package com.dasheck.materialminesweeper.fragments.game.interactors;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface IsGameWonInteractor {

  Observable<Boolean> execute();
}

package com.dasheck.materialminesweeper.fragments.game.interactors;

import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface PauseGameInteractor {

  Observable<Void> execute(boolean pause);
}

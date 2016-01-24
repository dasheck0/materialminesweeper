package com.dasheck.materialminesweeper.fragments.game.interactors;

import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public interface StartGameTimeInteractor {

  Observable<Void> execute();
}

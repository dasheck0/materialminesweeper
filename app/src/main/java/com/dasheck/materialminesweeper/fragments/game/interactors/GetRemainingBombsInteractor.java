package com.dasheck.materialminesweeper.fragments.game.interactors;

import rx.Observable;

/**
 * Created by s.neidig on 24/01/16.
 */
public interface GetRemainingBombsInteractor {

  Observable<Integer> execute();
}

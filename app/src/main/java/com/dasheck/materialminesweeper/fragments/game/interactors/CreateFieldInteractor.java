package com.dasheck.materialminesweeper.fragments.game.interactors;

import android.support.v4.util.Pair;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public interface CreateFieldInteractor {

  Observable<Pair<Integer, Integer>> execute();
}

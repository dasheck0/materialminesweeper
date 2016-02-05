package com.dasheck.materialminesweeper.fragments.game.interactors;

import android.support.v4.util.Pair;
import com.dasheck.model.models.Configuration;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public interface CreateFieldInteractor {

  Observable<Pair<Integer, Integer>> execute(Configuration configuration);
}

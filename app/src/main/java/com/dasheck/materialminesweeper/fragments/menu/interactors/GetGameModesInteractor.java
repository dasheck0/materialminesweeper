package com.dasheck.materialminesweeper.fragments.menu.interactors;

import com.dasheck.model.models.GameMode;
import java.util.List;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface GetGameModesInteractor {

  Observable<List<GameMode>> execute();
}

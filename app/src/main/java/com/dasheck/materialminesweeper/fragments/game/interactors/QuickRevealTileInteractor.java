package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.models.Position;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface QuickRevealTileInteractor {

  Observable<Boolean> execute(Position position);
}

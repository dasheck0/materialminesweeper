package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.models.Tile;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public interface MarkTileInteractor {

  Observable<Void> execute(Tile tile);
}

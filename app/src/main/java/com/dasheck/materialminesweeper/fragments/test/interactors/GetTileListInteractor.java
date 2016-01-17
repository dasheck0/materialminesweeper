package com.dasheck.materialminesweeper.fragments.test.interactors;

import com.dasheck.model.models.Tile;
import java.util.List;
import rx.Observable;

/**
 * Created by s.neidig on 17/01/16.
 */
public interface GetTileListInteractor {

  Observable<List<Tile>> execute();
}

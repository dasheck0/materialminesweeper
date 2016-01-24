package com.dasheck.materialminesweeper.fragments.game.interactors;

import android.support.v4.util.Pair;
import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Position;
import com.dasheck.model.models.Tile;
import com.dasheck.model.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.functions.Func2;

/**
 * Created by s.neidig on 17/01/16.
 */
public class GetTileListInteractorImpl implements GetTileListInteractor {

  @Inject FieldDatastore fieldDatastore;

  @Inject public GetTileListInteractorImpl() {
  }

  @Override public Observable<List<Tile>> execute() {
    return fieldDatastore.get()
        .map(field1 -> field1.getTiles().values())
        .flatMap(Observable::from)
        .toSortedList((tile, tile2) -> tile.getPosition().compareTo(tile2.getPosition()));
  }
}

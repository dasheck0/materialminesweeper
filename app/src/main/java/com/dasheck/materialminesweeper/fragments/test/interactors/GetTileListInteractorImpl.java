package com.dasheck.materialminesweeper.fragments.test.interactors;

import android.support.v4.util.Pair;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Tile;
import com.dasheck.model.utilities.Utilities;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 17/01/16.
 */
public class GetTileListInteractorImpl implements GetTileListInteractor {

  @Inject public GetTileListInteractorImpl() {
  }

  @Override public Observable<List<Tile>> execute() {
    Field field = Utilities.createField(20, 20, 30);

    List<Tile> tiles = new ArrayList<>();

    for(int x = 0; x < field.getWidth(); x ++) {
      for(int y = 0; y < field.getHeight(); y ++) {
        Tile tile = new Tile(x, y, field.getBombs().containsKey(new Pair<>(x, y)), false);
        tiles.add(tile);
      }
    }

    return Observable.just(tiles);
  }
}

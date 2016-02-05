package com.dasheck.materialminesweeper.fragments.game.interactors;

import com.dasheck.model.controllers.CurrentGameController;
import com.dasheck.model.models.Tile;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

/**
 * Created by s.neidig on 17/01/16.
 */
public class GetTileListInteractorImpl implements GetTileListInteractor {

  @Inject CurrentGameController currentGameController;

  @Inject public GetTileListInteractorImpl() {
  }

  @Override public Observable<List<Tile>> execute() {
    return currentGameController.getField()
        .map(field -> field.getTiles().values())
        .flatMap(Observable::from)
        .toSortedList((tile, tile2) -> tile.getPosition().compareTo(tile2.getPosition()));
  }
}

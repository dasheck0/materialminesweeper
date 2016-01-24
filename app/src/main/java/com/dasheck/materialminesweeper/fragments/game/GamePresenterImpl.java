package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileABombInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.MarkTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.model.models.Tile;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by s.neidig on 1701/16.
 */
public class GamePresenterImpl extends BasePresenterImpl implements GamePresenter {

  @Inject GameView view;
  @Inject CreateFieldInteractor createFieldInteractor;
  @Inject GetTileListInteractor getTileListInteractor;
  @Inject RevealTileInteractor revealTileInteractor;
  @Inject MarkTileInteractor markTileInteractor;
  @Inject IsTileABombInteractor isTileABombInteractor;

  @Override public void onResume() {
    super.onResume();

    createFieldInteractor.execute().subscribe(dimension -> {
      view.setDimension(dimension.first, dimension.second);
      getTileListInteractor.execute().subscribe(tiles -> {
        view.setTiles(tiles);
        view.repositionGrid(dimension.first, dimension.second);
      }, Throwable::printStackTrace);
    });
  }

  @Override public void revealTile(Tile tile) {
    Observable.zip(isTileABombInteractor.execute(tile), revealTileInteractor.execute(tile),
        (first, second) -> {
          if (first) {
            Timber.d("You lost");
          }
          return tile;
        })
        .flatMap(tile1 -> getTileListInteractor.execute())
        .subscribe(view::setTiles, Throwable::printStackTrace);
  }

  @Override public void markTile(Tile tile) {
    markTileInteractor.execute(tile)
        .flatMap(x -> getTileListInteractor.execute())
        .subscribe(view::setTiles);
  }
}

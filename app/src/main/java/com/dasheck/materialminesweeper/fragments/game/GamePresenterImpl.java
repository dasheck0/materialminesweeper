package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.model.models.Tile;
import javax.inject.Inject;
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

  @Override public void onResume() {
    super.onResume();

    createFieldInteractor.execute().subscribe(dimension -> {
      view.setDimension(dimension.first, dimension.second);
      getTileListInteractor.execute().subscribe(view::setTiles, Throwable::printStackTrace);
    });
  }

  @Override public void revealTile(Tile tile) {
    revealTileInteractor.execute(tile)
        .flatMap(x -> getTileListInteractor.execute())
        .subscribe(view::setTiles);
  }
}

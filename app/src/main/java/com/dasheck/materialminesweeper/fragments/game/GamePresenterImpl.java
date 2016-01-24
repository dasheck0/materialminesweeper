package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetElapsedTimeInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetRemainingBombsInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileABombInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.MarkTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.StartGameTimeInteractor;
import com.dasheck.model.models.Tile;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
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
  @Inject GetRemainingBombsInteractor getRemainingBombsInteractor;
  @Inject GetElapsedTimeInteractor getElapsedTimeInteractor;
  @Inject StartGameTimeInteractor startGameTimeInteractor;

  private Observable<Long> timer;
  private Subscriber<Long> timerSubscription;

  @Override public void onResume() {
    super.onResume();

    view.startNewGame();
    timer = Observable.interval(1, TimeUnit.SECONDS)
        .flatMap(x -> getElapsedTimeInteractor.execute())
        .observeOn(AndroidSchedulers.mainThread());

    timerSubscription = new Subscriber<Long>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {

      }

      @Override public void onNext(Long aLong) {
        view.setElapsedTime(aLong);
      }
    };
  }

  @Override public void onStop() {
    super.onStop();
  }

  @Override public void revealTile(Tile tile) {
    Observable.zip(isTileABombInteractor.execute(tile), revealTileInteractor.execute(tile),
        (isTileABomb, second) -> {
          if (isTileABomb) {
            view.showGameLostDialog();
            timerSubscription.unsubscribe();
          }
          return tile;
        }).flatMap(x -> updateGameInformation()).subscribe(x -> {
    }, Throwable::printStackTrace);
  }

  @Override public void markTile(Tile tile) {
    markTileInteractor.execute(tile).flatMap(x -> updateGameInformation()).subscribe();
  }

  @Override public void startGame(int columnCount, int rowCount, int difficulty) {
    startGameTimeInteractor.execute()
        .flatMap(y -> createFieldInteractor.execute(columnCount, rowCount, difficulty).
            flatMap(dimension -> updateGameInformation().map(x -> dimension)))
        .subscribe(dimension -> {
          view.setDimension(dimension.first, dimension.second);
          view.repositionGrid(dimension.first, dimension.second);
        }, Throwable::printStackTrace);

    timer.subscribe(timerSubscription);
  }

  private Observable<Void> updateGameInformation() {
    return Observable.zip(getTileListInteractor.execute(), getRemainingBombsInteractor.execute(),
        (tiles, remainingBombs) -> {
          view.setTiles(tiles);
          view.setNumberOfRemainingBombs(remainingBombs);
          return null;
        });
  }
}

package com.dasheck.materialminesweeper.fragments.game;

import com.dasheck.materialminesweeper.activities.Navigator;
import com.dasheck.materialminesweeper.fragments.BasePresenterImpl;
import com.dasheck.materialminesweeper.fragments.game.interactors.CreateFieldInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetElapsedTimeInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetGameInformationInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetRemainingBombsInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.GetTileListInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsGameWonInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileABombInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.IsTileRevealedInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.MarkTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.QuickRevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.SaveLatestGameInformationInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.StartGameTimeInteractor;
import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Configuration;
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
  @Inject GetGameInformationInteractor getGameInformationInteractor;
  @Inject IsGameWonInteractor isGameWonInteractor;
  @Inject IsTileRevealedInteractor isTileRevealedInteractor;
  @Inject QuickRevealTileInteractor quickRevealTileInteractor;
  @Inject SaveLatestGameInformationInteractor saveLatestGameInformationInteractor;
  @Inject Navigator navigator;

  private Observable<Long> timer;
  private Subscriber<Long> timerSubscription;

  private Configuration configuration;

  @Override public void onResume() {
    super.onResume();

    if (configuration != null) {
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

      startGame(configuration.getWidth(), configuration.getHeight(), configuration.getDifficulty());
    }
  }

  @Override public void onStop() {
    super.onStop();
  }

  @Override public void revealTile(Tile tile) {
    Observable<Boolean> revealObservable = isTileRevealedInteractor.execute(tile.getPosition())
        .flatMap(isRevealed -> isRevealed ? quickRevealTileInteractor.execute(tile.getPosition())
            : revealTileInteractor.execute(tile));

    revealObservable.map(playerLost -> {
      if (playerLost) {
        timerSubscription.unsubscribe();
      }

      return playerLost;
    })
        .flatMap(isBomb -> Observable.zip(isGameWonInteractor.execute(), getGameInformationInteractor.execute(),
            (gameWon, gameInformation) -> {
              if (isBomb) {
                view.showGameLostDialog(gameInformation);
              } else if (gameWon) {
                view.showGameWonDialog();
              }

              return gameInformation;
            }))
        .flatMap(saveLatestGameInformationInteractor::execute)
        .flatMap(x -> updateGameInformation())
        .subscribe();
  }

  @Override public void markTile(Tile tile) {
    markTileInteractor.execute(tile).flatMap(x -> updateGameInformation()).subscribe();
  }

  @Override public void startGame(int columnCount, int rowCount, int difficulty) {
    startGameTimeInteractor.execute().flatMap(y -> createFieldInteractor.execute(columnCount, rowCount, difficulty).
        flatMap(dimension -> updateGameInformation().map(x -> dimension))).subscribe(dimension -> {
      view.setDimension(dimension.first, dimension.second);
      view.repositionGrid(dimension.first, dimension.second);
    }, Throwable::printStackTrace);

    timer.subscribe(timerSubscription);
  }

  @Override public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override public void showMenu() {
    navigator.showMenu();
  }

  @Override public void restartGame() {
    if (configuration != null) {
      startGame(configuration.getWidth(), configuration.getHeight(), configuration.getDifficulty());
    }
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

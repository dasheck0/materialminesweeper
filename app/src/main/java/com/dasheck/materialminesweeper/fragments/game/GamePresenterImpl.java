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
import com.dasheck.materialminesweeper.fragments.game.interactors.PauseGameInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.QuickRevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.RevealTileInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.SaveLatestGameInformationInteractor;
import com.dasheck.materialminesweeper.fragments.game.interactors.StopGameInteractor;
import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Tile;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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
  @Inject GetGameInformationInteractor getGameInformationInteractor;
  @Inject IsGameWonInteractor isGameWonInteractor;
  @Inject IsTileRevealedInteractor isTileRevealedInteractor;
  @Inject QuickRevealTileInteractor quickRevealTileInteractor;
  @Inject SaveLatestGameInformationInteractor saveLatestGameInformationInteractor;
  @Inject PauseGameInteractor pauseGameInteractor;
  @Inject StopGameInteractor stopGameInteractor;
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

      startGame(configuration);
    }
  }

  @Override public void onStop() {
    super.onStop();
  }

  @Override public void revealTile(Tile tile) {
    view.setSmileyChecking();

    Observable<Boolean> revealObservable = isTileRevealedInteractor.execute(tile.getPosition())
        .flatMap(isRevealed -> isRevealed ? quickRevealTileInteractor.execute(tile.getPosition())
            : revealTileInteractor.execute(tile));

    revealObservable.flatMap(
        isBomb -> Observable.zip(isGameWonInteractor.execute(), getGameInformationInteractor.execute(),
            (gameWon, gameInformation) -> {
              view.setSmileyDefault();

              if (isBomb) {
                view.setSmileyLost();
                view.freezeField();
                stopGameInteractor.execute()
                    .flatMap(x -> saveLatestGameInformationInteractor.execute(gameInformation))
                    .subscribe();
              } else if (gameWon) {
                view.setSmileyWon();
                view.freezeField();
                stopGameInteractor.execute()
                    .flatMap(x -> saveLatestGameInformationInteractor.execute(gameInformation))
                    .subscribe();
              }

              return gameInformation;
            })).flatMap(x -> updateGameInformation()).subscribe();
  }

  @Override public void markTile(Tile tile) {
    markTileInteractor.execute(tile).flatMap(x -> updateGameInformation()).subscribe();
  }

  @Override public void startGame(Configuration configuration) {
    view.setSmileyDefault();
    view.unfreezeField();

    createFieldInteractor.execute(configuration).
        flatMap(dimension -> updateGameInformation().map(x -> dimension)).subscribe(dimension -> {
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
      startGame(configuration);
    }
  }

  @Override public void loadMenu() {
    navigator.showMenu();
  }

  @Override public void pauseGame() {
    pauseGameInteractor.execute(true);
  }

  @Override public void unpauseGame() {
    pauseGameInteractor.execute(false);
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

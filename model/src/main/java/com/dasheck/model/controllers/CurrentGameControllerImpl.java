package com.dasheck.model.controllers;

import android.graphics.Bitmap;
import com.dasheck.model.controllers.field_controllers.FieldController;
import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.models.Position;
import javax.inject.Inject;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public class CurrentGameControllerImpl implements CurrentGameController {

  @Inject GameTimeController gameTimeController;
  @Inject FieldController fieldController;

  private Configuration configuration;
  private Field field;

  @Inject public CurrentGameControllerImpl() {
  }

  @Override public Observable<Void> startGame(Configuration configuration) {
    return fieldController.create(configuration).doOnNext(created -> {
      this.field = created;
      this.configuration = configuration;
    }).flatMap(x -> gameTimeController.reset());
  }

  @Override public Observable<Field> getField() {
    return Observable.just(field);
  }

  @Override public Observable<GameInformation> createGameInformation() {
    return Observable.zip(fieldController.isGameWon(field), fieldController.getNumberOfRevealedTiles(field),
        fieldController.getNumberOfMarkedTiles(field), gameTimeController.getElapsed(),
        (isWon, revealedTileCount, markedTileCount, elapsed) -> new GameInformation(isWon, configuration.getWidth(),
            configuration.getHeight(), configuration.getDifficulty(), configuration.getBombCount(), revealedTileCount,
            markedTileCount, elapsed));
  }

  /* Field manipulation */

  @Override public Observable<Boolean> revealTile(Position position) {
    return fieldController.revealTile(field, position);
  }

  @Override public Observable<Void> markTile(Position position) {
    return fieldController.markTile(field, position);
  }

  @Override public Observable<Boolean> isTileABomb(Position position) {
    return fieldController.isTileABomb(field, position);
  }

  @Override public Observable<Integer> getNumberOfRemainingBombs() {
    return fieldController.getNumberOfRemainingBombs(field);
  }

  @Override public Observable<Boolean> isGameWon() {
    return fieldController.isGameWon(field);
  }

  @Override public Observable<Boolean> isTileRevealed(Position position) {
    return fieldController.isTileRevealed(field, position);
  }

  @Override public Observable<Boolean> quickRevealTile(Position position) {
    return fieldController.quickRevealTile(field, position);
  }
}

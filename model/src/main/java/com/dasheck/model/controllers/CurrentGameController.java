package com.dasheck.model.controllers;

import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.GameInformation;
import com.dasheck.model.models.GameMode;
import com.dasheck.model.models.Position;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface CurrentGameController {

  Observable<Void> startGame(Configuration configuration);

  Observable<Void> stopGame();

  Observable<Field> getField();

  Observable<GameInformation> createGameInformation();

  /* Field manipulation */

  Observable<Boolean> revealTile(Position position);

  Observable<Void> markTile(Position position);

  Observable<Boolean> isTileABomb(Position position);

  Observable<Integer> getNumberOfRemainingBombs();

  Observable<Boolean> isGameWon();

  Observable<Boolean> isTileRevealed(Position position);

  Observable<Boolean> quickRevealTile(Position position);
}

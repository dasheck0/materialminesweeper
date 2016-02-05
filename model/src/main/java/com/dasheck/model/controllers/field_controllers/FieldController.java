package com.dasheck.model.controllers.field_controllers;

import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Position;
import rx.Observable;

/**
 * @author Stefan Neidig
 */
public interface FieldController {

  Observable<Field> create(int width, int height, int bombCount);

  Observable<Field> create(Configuration configuration);

  Observable<Boolean> revealTile(Field field, Position position);

  Observable<Void> markTile(Field field, Position position);

  Observable<Boolean> isTileABomb(Field field, Position position);

  Observable<Boolean> isTileRevealed(Field field, Position position);

  Observable<Integer> getNumberOfRemainingBombs(Field field);

  Observable<Boolean> isGameWon(Field field);

  Observable<Boolean> quickRevealTile(Field field, Position position);

  Observable<Integer> getNumberOfRevealedTiles(Field field);

  Observable<Integer> getNumberOfMarkedTiles(Field field);

}

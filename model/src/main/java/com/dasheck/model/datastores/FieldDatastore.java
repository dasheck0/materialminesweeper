package com.dasheck.model.datastores;

import com.dasheck.model.models.Field;
import com.dasheck.model.models.Position;
import rx.Observable;

/**
 * Created by s.neidig on 23/01/16.
 */
public interface FieldDatastore {

  public static final int DIFFICULTY_EASY = 0;

  public static final int DIFFICULTY_MEDIUM = 1;

  public static final int DIFFICULTY_HARD = 2;

  public static final int DIFFICULTY_XMETIRX = 3;

  Observable<Field> create(int width, int height, int difficulty);

  Observable<Field> get();

  Observable<Void> revealTile(Position position);

  Observable<Void> markTile(Position position);

  Observable<Boolean> isTileABomb(Position position);

  Observable<Integer> getNumberOfRemainingBombs();

}

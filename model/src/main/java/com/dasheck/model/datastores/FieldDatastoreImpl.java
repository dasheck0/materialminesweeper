package com.dasheck.model.datastores;

import com.dasheck.model.models.Field;
import com.dasheck.model.models.Position;
import com.dasheck.model.models.Tile;
import com.dasheck.model.utilities.Utilities;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Created by s.neidig on 23/01/16.
 */
public class FieldDatastoreImpl implements FieldDatastore {

  private Field field;

  @Inject public FieldDatastoreImpl() {
  }

  @Override public Observable<Field> create(int width, int height, int difficulty) {
    return Observable.create(new Observable.OnSubscribe<Field>() {
      @Override public void call(Subscriber<? super Field> subscriber) {
        field = Utilities.createField(width, height,
            getNumberOfBombsForDiffculty(difficulty, width * height));
        subscriber.onNext(field);
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Field> get() {
    if (field == null) {
      throw new IllegalStateException("You cannot retrive a field without creating it");
    } else {
      return Observable.just(field);
    }
  }

  @Override public Observable<Void> revealTile(Position position) {
    if (field == null) {
      throw new IllegalStateException("You cannot retrieve a field without creating it");
    } else {
      return Observable.create(new Observable.OnSubscribe<Position>() {
        @Override public void call(Subscriber<? super Position> subscriber) {
          revealTileRecursive(position);
/*          Tile tile = field.getTiles().get(position);
          if (!tile.isRevealed()) {
            field.getTiles().get(position).setIsRevealed(true);
          }

          if(tile.getNumberOfAdjacentBombs() == 0) {
            List
          }
*/
          subscriber.onNext(position);
          subscriber.onCompleted();
        }
      }).map(x -> null);
    }
  }

  private void revealTileRecursive(Position position) {
    Tile tile = field.getTiles().get(position);
    if (!tile.isRevealed()) {
      tile.setIsRevealed(true);

      if (tile.getNumberOfAdjacentBombs() == 0) {
        List<Position> neighbours =
            Utilities.getAdjacentTiles(field.getWidth(), field.getHeight(), position);
        for (Position neighbour : neighbours) {
          Tile neighbourTile = field.getTiles().get(neighbour);
          if (!neighbourTile.isRevealed()) {
            revealTileRecursive(neighbour);
          }
        }
      }
    }
  }

  @Override public Observable<Boolean> isTileABomb(Position position) {
    return null;
  }

  private int getNumberOfBombsForDiffculty(int difficulty, int size) {
    float percentage = (difficulty + 1) * 15.0f / 100.0f;
    return (int) (percentage * size);
  }
}

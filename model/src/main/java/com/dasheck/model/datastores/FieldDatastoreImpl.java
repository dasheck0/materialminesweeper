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
      throw new IllegalStateException("You cannot use a field without creating it");
    } else {
      return Observable.just(field);
    }
  }

  @Override public Observable<Void> revealTile(Position position) {
    if (field == null) {
      throw new IllegalStateException("You cannot use a field without creating it");
    } else {
      return Observable.create(new Observable.OnSubscribe<Position>() {
        @Override public void call(Subscriber<? super Position> subscriber) {
          revealTileRecursive(position);

          subscriber.onNext(position);
          subscriber.onCompleted();
        }
      }).map(x -> null);
    }
  }

  @Override public Observable<Void> markTile(Position position) {
    if (field == null) {
      throw new IllegalStateException("You cannot use a field without creating it");
    } else {
      return Observable.create(new Observable.OnSubscribe<Void>() {
        @Override public void call(Subscriber<? super Void> subscriber) {
          Tile tile = field.getTiles().get(position);
          if (!tile.isRevealed()) {
            tile.setIsMarked(!tile.isMarked());
          }

          subscriber.onNext(null);
          subscriber.onCompleted();
        }
      });
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
    if(field == null) {
      throw new IllegalStateException("You cannot use a field without creating it");
    } else {
      return Observable.create(new Observable.OnSubscribe<Boolean>() {
        @Override public void call(Subscriber<? super Boolean> subscriber) {
          Tile tile = field.getTiles().get(position);
          subscriber.onNext(tile.isBomb());
          subscriber.onCompleted();
        }
      });
    }
  }

  private int getNumberOfBombsForDiffculty(int difficulty, int size) {
    float percentage = (difficulty + 1) * 15.0f / 100.0f;
    return (int) (percentage * size);
  }
}

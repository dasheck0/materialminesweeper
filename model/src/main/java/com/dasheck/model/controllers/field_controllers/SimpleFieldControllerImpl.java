package com.dasheck.model.controllers.field_controllers;

import android.util.Pair;
import com.dasheck.model.models.Configuration;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Position;
import com.dasheck.model.models.Tile;
import com.dasheck.model.utilities.Utilities;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

/**
 * @author Stefan Neidig
 */
public class SimpleFieldControllerImpl implements FieldController {

  @Inject public SimpleFieldControllerImpl() {
  }

  @Override public Observable<Field> create(int width, int height, int bombCount) {
    return Observable.create(new Observable.OnSubscribe<Field>() {
      @Override public void call(Subscriber<? super Field> subscriber) {
        subscriber.onNext(Utilities.createField(width, height, bombCount));
        subscriber.onCompleted();
      }
    });
  }

  @Override public Observable<Field> create(Configuration configuration) {
    return create(configuration.getWidth(), configuration.getHeight(), configuration.getBombCount());
  }

  @Override public Observable<Boolean> revealTile(Field field, Position position) {
    return getTile(field, position).map(tile -> {
      if (!tile.isRevealed()) {
        revealTileRecursive(field, position, true);
      }

      return position;
    }).flatMap(p -> isTileABomb(field, p));
  }

  @Override public Observable<Void> markTile(Field field, Position position) {
    return getTile(field, position).map(tile -> {
      if (!tile.isRevealed()) {
        tile.setIsMarked(!tile.isMarked());
      }

      return null;
    });
  }

  @Override public Observable<Boolean> isTileABomb(Field field, Position position) {
    return getTile(field, position).map(Tile::isBomb);
  }

  @Override public Observable<Boolean> isTileRevealed(Field field, Position position) {
    return getTile(field, position).map(Tile::isRevealed);
  }

  @Override public Observable<Integer> getNumberOfRemainingBombs(Field field) {
    return getTiles(field).map(Map::values)
        .flatMap(Observable::from)
        .map(tile -> new Pair<>(tile.isBomb() ? 1 : 0, tile.isMarked() ? 1 : 0))
        .toList()
        .map(list -> {
          int result = 0;

          for (Pair<Integer, Integer> integerIntegerPair : list) {
            result += integerIntegerPair.first;
            result -= integerIntegerPair.second;
          }

          return result;
        });
  }

  @Override public Observable<Boolean> isGameWon(Field field) {
    return getTiles(field).map(Map::values)
        .flatMap(Observable::from)
        .filter(tile -> tile.isRevealed() || tile.isBomb())
        .toList()
        .map(list -> list.size() == field.getTiles().values().size());
  }

  @Override public Observable<Boolean> quickRevealTile(Field field, Position position) {
    return getTile(field, position).map(tile -> {
      if (tile.isRevealed()) {
        return quickReveal(field, position);
      }

      return false;
    });
  }

  @Override public Observable<Integer> getNumberOfRevealedTiles(Field field) {
    return getTiles(field).map(Map::values).flatMap(Observable::from).filter(Tile::isRevealed).count();
  }

  @Override public Observable<Integer> getNumberOfMarkedTiles(Field field) {
    return getTiles(field).map(Map::values).flatMap(Observable::from).filter(Tile::isMarked).count();
  }

  private Observable<Map<Position, Tile>> getTiles(Field field) {
    return Observable.just(field.getTiles());
  }

  private Observable<Tile> getTile(Field field, Position position) {
    return getTiles(field).map(tiles -> tiles.get(position));
  }

  private void revealTileRecursive(Field field, Position position, boolean exposeBombs) {
    Tile tile = field.getTiles().get(position);
    if (!tile.isRevealed()) {
      if (!tile.isBomb() || exposeBombs) {
        tile.setIsRevealed(true);
        tile.setIsMarked(false);

        if (tile.getNumberOfAdjacentBombs() == 0) {
          List<Position> neighbours = Utilities.getAdjacentTiles(field.getWidth(), field.getHeight(), position);
          for (Position neighbour : neighbours) {
            Tile neighbourTile = field.getTiles().get(neighbour);
            if (!neighbourTile.isRevealed()) {
              revealTileRecursive(field, neighbour, exposeBombs);
            }
          }
        }
      }
    }
  }

  private boolean quickReveal(Field field, Position position) {
    List<Position> neighbours = Utilities.getAdjacentTiles(field.getWidth(), field.getHeight(), position);

    int unrevealedBombCount = 0;
    int trulyMarkedBombCount = 0;
    int markedBombCount = 0;
    for (Position neighbour : neighbours) {
      Tile neighbourTile = field.getTiles().get(neighbour);
      if (!neighbourTile.isRevealed() && neighbourTile.isBomb()) {
        unrevealedBombCount++;
      }

      if (neighbourTile.isBomb() && neighbourTile.isMarked()) {
        trulyMarkedBombCount++;
      }

      if (neighbourTile.isMarked()) {
        markedBombCount++;
      }
    }

    unrevealedBombCount -= trulyMarkedBombCount;

    boolean playerHitBomb = false;

    if (unrevealedBombCount == 0) {
      for (Position neighbour : neighbours) {
        revealTileRecursive(field, neighbour, false);

        /*Tile neighbourTile = field.getTiles().get(neighbour);
        if (!neighbourTile.isRevealed() && !neighbourTile.isBomb()) {
          neighbourTile.setIsMarked(false);
          neighbourTile.setIsRevealed(true);
        }*/
      }
    } else {
      if (trulyMarkedBombCount != markedBombCount) {
        playerHitBomb = true;
      }
    }

    return playerHitBomb;
  }
}

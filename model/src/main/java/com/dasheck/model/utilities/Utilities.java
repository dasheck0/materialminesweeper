package com.dasheck.model.utilities;

import android.support.v4.util.Pair;
import com.dasheck.model.datastores.FieldDatastore;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Position;
import com.dasheck.model.models.Tile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import timber.log.Timber;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Utilities {

  // TODO: 23/01/16 Not every effective. Refactor it
  public static Field createField(int width, int height, int numberOfBombs) {
    Field field = new Field(width, height, new HashMap<>());

    Map<Position, Tile> tiles = new HashMap<>();

    List<Position> available = new ArrayList<>();

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        available.add(new Position(x, y));
      }
    }

    List<Position> bombs = new ArrayList<>();

    for (int i = 0; i < numberOfBombs; i++) {
      int index = new Random().nextInt(available.size());
      Position position = available.get(index);

      bombs.add(position);
      available.remove(index);
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        Position temp = new Position(x, y);
        boolean isBomb = bombs.contains(temp);
        int numberOfAdjacentBombs = numberOfIntersectingBombs(getAdjacentTiles(width, height, temp), bombs);

        tiles.put(temp, new Tile(temp, numberOfAdjacentBombs, isBomb, false, false));
      }
    }

    field.setTiles(tiles);
    return field;
  }

  public static List<Position> getAdjacentTiles(int width, int height, Position position) {
    List<Position> result = new ArrayList<>();

    for (int x = -1; x < 2; x++) {
      for (int y = -1; y < 2; y++) {
        Position temp = new Position(position.getX() + x, position.getY() + y);

        if (temp.getX() >= 0 && temp.getX() < width && temp.getY() >= 0 && temp.getY() < height && !temp.equals(
            position)) {
          result.add(temp);
        }
      }
    }

    return result;
  }

  public static String getReadbaleNameFromDifficulty(int difficulty) {
    switch (difficulty) {
      case FieldDatastore.DIFFICULTY_EASY:
        return String.format("Easy (%.0f%%) bombs", getRelativeBombCount(difficulty) * 100.0f);
      case FieldDatastore.DIFFICULTY_MEDIUM:
        return String.format("Medium (%.0f%%) bombs", getRelativeBombCount(difficulty) * 100.0f);
      case FieldDatastore.DIFFICULTY_HARD:
        return String.format("Hard (%.0f%%) bombs", getRelativeBombCount(difficulty) * 100.0f);
      case FieldDatastore.DIFFICULTY_XMETIRX:
        return String.format("Expert (%.0f%%) bombs", getRelativeBombCount(difficulty) * 100.0f);
      default:
        return "";
    }
  }

  public static float getRelativeBombCount(int difficulty) {
    return (difficulty + 1) * 15.0f / 100.0f;
  }

  public static int getNumberOfBombsForDiffculty(int difficulty, int size) {
    return (int) (getRelativeBombCount(difficulty) * size);
  }

  private static int numberOfIntersectingBombs(List<Position> tiles, List<Position> bombs) {
    int result = 0;

    for (Position tile : tiles) {
      if (bombs.contains(tile)) {
        result++;
      }
    }

    return result;
  }
}

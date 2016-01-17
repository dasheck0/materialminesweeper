package com.dasheck.model.utilities;

import android.support.v4.util.Pair;
import com.dasheck.model.models.Field;
import com.dasheck.model.models.Tile;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Utilities {

  public static Field createField(int width, int height, int numberOfBombs) {
    Field field = new Field(width, height,
        new HashMap<>());

    Map<Pair<Integer, Integer>, Tile> bombs = new HashMap<>();

    for(int i = 0; i < numberOfBombs; i ++) {
      int x = new Random().nextInt(width);
      int y = new Random().nextInt(height);

      if(!bombs.containsKey(new Pair<>(x, y))) {
        bombs.put(new Pair<>(x, y), new Tile(x, y, true, false));
      }
    }

    field.setBombs(bombs);

    return field;
  }
}

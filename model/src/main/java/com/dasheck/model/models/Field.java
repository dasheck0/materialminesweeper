package com.dasheck.model.models;

import android.support.v4.util.Pair;
import java.security.Policy;
import java.util.Map;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Field {

  private Map<Position, Tile> tiles;
  private int width;
  private int height;

  public Field() {
  }

  public Field(int width, int height, Map<Position, Tile> tiles) {
    this.width = width;
    this.height = height;
    this.tiles = tiles;
  }

  public Map<Position, Tile> getTiles() {
    return tiles;
  }

  public void setTiles(Map<Position, Tile> tiles) {
    this.tiles = tiles;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  @Override public String toString() {
    return "Field{" +
        ", width=" + width +
        ", height=" + height +
        "tiles=" + tiles +
        '}';
  }
}

package com.dasheck.model.models;

import android.support.v4.util.Pair;
import java.security.Policy;
import java.util.Map;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Field {

  private Map<Pair<Integer, Integer>, Tile> bombs;
  private int width;
  private int height;

  public Field() {
  }

  public Field(int width, int height, Map<Pair<Integer, Integer>, Tile> bombs) {
    this.width = width;
    this.height = height;
    this.bombs = bombs;
  }

  public Map<Pair<Integer, Integer>, Tile> getBombs() {
    return bombs;
  }

  public void setBombs(Map<Pair<Integer, Integer>, Tile> bombs) {
    this.bombs = bombs;
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
        "bombs=" + bombs +
        '}';
  }
}

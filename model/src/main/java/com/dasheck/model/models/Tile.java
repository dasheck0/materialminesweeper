package com.dasheck.model.models;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Tile {

  private int x;
  private int y;
  private boolean isBomb;
  private boolean isMarked;

  public Tile() {
  }

  public Tile(int x, int y, boolean isBomb, boolean isMarked) {
    this.x = x;
    this.y = y;
    this.isBomb = isBomb;
    this.isMarked = isMarked;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public boolean isBomb() {
    return isBomb;
  }

  public void setIsBomb(boolean isBomb) {
    this.isBomb = isBomb;
  }

  public boolean isMarked() {
    return isMarked;
  }

  public void setIsMarked(boolean isMarked) {
    this.isMarked = isMarked;
  }

  @Override public String toString() {
    return "Tile{" +
        "x=" + x +
        ", y=" + y +
        ", isBomb=" + isBomb +
        ", isMarked=" + isMarked +
        '}';
  }
}

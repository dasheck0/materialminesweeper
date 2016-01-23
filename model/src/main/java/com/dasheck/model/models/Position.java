package com.dasheck.model.models;

/**
 * Created by s.neidig on 23/01/16.
 */
public class Position implements Comparable<Position> {

  int x;
  int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
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

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Position position = (Position) o;

    if (x != position.x) return false;
    return y == position.y;
  }

  @Override public int hashCode() {
    int result = x;
    result = 31 * result + y;
    return result;
  }

  @Override public String toString() {
    return "Position{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  @Override public int compareTo(Position another) {
    if (this.y == another.getY()) {
      return Integer.valueOf(this.x).compareTo(another.getX());
    }

    return Integer.valueOf(this.y).compareTo(another.getY());
  }
}

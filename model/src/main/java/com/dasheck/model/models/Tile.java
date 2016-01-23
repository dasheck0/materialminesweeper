package com.dasheck.model.models;

/**
 * Created by s.neidig on 17/01/16.
 */
public class Tile {

  private Position position;
  private int numberOfAdjacentBombs;
  private boolean isBomb;
  private boolean isMarked;
  private boolean isRevealed;

  public Tile() {
  }

  public Tile(Position position, int numberOfAdjacentBombs, boolean isBomb, boolean isMarked,
      boolean isRevealed) {
    this.position = position;
    this.numberOfAdjacentBombs = numberOfAdjacentBombs;
    this.isBomb = isBomb;
    this.isMarked = isMarked;
    this.isRevealed = isRevealed;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
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

  public int getNumberOfAdjacentBombs() {
    return numberOfAdjacentBombs;
  }

  public void setNumberOfAdjacentBombs(int numberOfAdjacentBombs) {
    this.numberOfAdjacentBombs = numberOfAdjacentBombs;
  }

  public boolean isRevealed() {
    return isRevealed;
  }

  public void setIsRevealed(boolean isRevealed) {
    this.isRevealed = isRevealed;
  }

  @Override public String toString() {
    return "Tile{" +
        "position=" + position +
        ", numberOfAdjacentBombs=" + numberOfAdjacentBombs +
        ", isBomb=" + isBomb +
        ", isMarked=" + isMarked +
        ", isRevealed=" + isRevealed +
        '}';
  }
}

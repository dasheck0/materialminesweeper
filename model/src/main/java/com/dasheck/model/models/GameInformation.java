package com.dasheck.model.models;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GameInformation {

  int markedTilesCount;
  int revealedTilesCount;
  long elapsedTime;

  public GameInformation(int markedTilesCount, int revealedTilesCount, long elapsedTime) {
    this.markedTilesCount = markedTilesCount;
    this.revealedTilesCount = revealedTilesCount;
    this.elapsedTime = elapsedTime;
  }

  public int getMarkedTilesCount() {
    return markedTilesCount;
  }

  public void setMarkedTilesCount(int markedTilesCount) {
    this.markedTilesCount = markedTilesCount;
  }

  public int getRevealedTilesCount() {
    return revealedTilesCount;
  }

  public void setRevealedTilesCount(int revealedTilesCount) {
    this.revealedTilesCount = revealedTilesCount;
  }

  public long getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(long elapsedTime) {
    this.elapsedTime = elapsedTime;
  }
}

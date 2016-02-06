package com.dasheck.model.models;

/**
 * Created by s.neidig on 24/01/16.
 */
public class GameInformation {

  private boolean won;
  private int boardWidth;
  private int boardHeight;
  private int difficulty;
  private int bombCount;
  private int revealedTilesCount;
  private int markedTilesCount;
  private long elapsedTime;

  public GameInformation() {
  }

  public GameInformation(boolean won, int boardWidth, int boardHeight, int difficulty, int bombCount,
      int revealedTilesCount, int markedTilesCount, long elapsedTime) {
    this.won = won;
    this.boardWidth = boardWidth;
    this.boardHeight = boardHeight;
    this.difficulty = difficulty;
    this.bombCount = bombCount;
    this.revealedTilesCount = revealedTilesCount;
    this.markedTilesCount = markedTilesCount;
    this.elapsedTime = elapsedTime;
  }

  public boolean isWon() {
    return won;
  }

  public void setWon(boolean won) {
    this.won = won;
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public void setBoardWidth(int boardWidth) {
    this.boardWidth = boardWidth;
  }

  public int getBoardHeight() {
    return boardHeight;
  }

  public void setBoardHeight(int boardHeight) {
    this.boardHeight = boardHeight;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public int getBombCount() {
    return bombCount;
  }

  public void setBombCount(int bombCount) {
    this.bombCount = bombCount;
  }

  public int getRevealedTilesCount() {
    return revealedTilesCount;
  }

  public void setRevealedTilesCount(int revealedTilesCount) {
    this.revealedTilesCount = revealedTilesCount;
  }

  public int getMarkedTilesCount() {
    return markedTilesCount;
  }

  public void setMarkedTilesCount(int markedTilesCount) {
    this.markedTilesCount = markedTilesCount;
  }

  public long getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(long elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  @Override public String toString() {
    return "GameInformation{" +
        "won=" + won +
        ", boardWidth=" + boardWidth +
        ", boardHeight=" + boardHeight +
        ", difficulty=" + difficulty +
        ", bombCount=" + bombCount +
        ", revealedTilesCount=" + revealedTilesCount +
        ", markedTilesCount=" + markedTilesCount +
        ", elapsedTime=" + elapsedTime +
        '}';
  }
}

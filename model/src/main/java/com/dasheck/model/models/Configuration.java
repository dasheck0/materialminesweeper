package com.dasheck.model.models;

/**
 * @author Stefan Neidig
 */
public class Configuration {

  private int width;
  private int height;
  private int bombCount;
  private int difficulty;

  public Configuration() {
  }

  public Configuration(int width, int height, int bombCount, int difficulty) {
    this.width = width;
    this.height = height;
    this.bombCount = bombCount;
    this.difficulty = difficulty;
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

  public int getBombCount() {
    return bombCount;
  }

  public void setBombCount(int bombCount) {
    this.bombCount = bombCount;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }
}

package com.dasheck.model.models;

/**
 * @author Stefan Neidig
 */
public class GameMode {

  private String name;
  private int mode;

  public GameMode() {
  }

  public GameMode(String name, int mode) {
    this.name = name;
    this.mode = mode;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMode() {
    return mode;
  }

  public void setMode(int mode) {
    this.mode = mode;
  }

  @Override public String toString() {
    return "GameMode{" +
        "name='" + name + '\'' +
        ", mode=" + mode +
        '}';
  }
}

package com.dasheck.model.models;

import java.util.List;

/**
 * @author Stefan Neidig
 */
public class GameMode {

  private String name;
  private int mode;
  private List<Configuration> configurations;

  public GameMode() {
  }

  public GameMode(String name, int mode, List<Configuration> configurations) {
    this.name = name;
    this.mode = mode;
    this.configurations = configurations;
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

  public List<Configuration> getConfigurations() {
    return configurations;
  }

  public void setConfigurations(List<Configuration> configurations) {
    this.configurations = configurations;
  }

  @Override public String toString() {
    return "GameMode{" +
        "name='" + name + '\'' +
        ", mode=" + mode +
        ", configurations=" + configurations +
        '}';
  }
}

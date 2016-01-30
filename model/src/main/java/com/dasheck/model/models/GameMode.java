package com.dasheck.model.models;

import java.util.List;

/**
 * @author Stefan Neidig
 */
public class GameMode {

  private String name;
  private List<Configuration> configurations;

  public GameMode() {
  }

  public GameMode(String name, List<Configuration> configurations) {
    this.name = name;
    this.configurations = configurations;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
        ", configurations=" + configurations +
        '}';
  }
}

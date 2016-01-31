package com.dasheck.model.models;

import java.util.List;

/**
 * @author Stefan Neidig
 */
public class GameMode {

  private String name;
  private List<Configuration> configurations;
  private List<GameInformation> gameInformationList;

  public GameMode() {
  }

  public GameMode(String name, List<Configuration> configurations, List<GameInformation> gameInformationList) {
    this.name = name;
    this.configurations = configurations;
    this.gameInformationList = gameInformationList;
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

  public List<GameInformation> getGameInformationList() {
    return gameInformationList;
  }

  public void setGameInformationList(List<GameInformation> gameInformationList) {
    this.gameInformationList = gameInformationList;
  }

  @Override public String toString() {
    return "GameMode{" +
        "name='" + name + '\'' +
        ", configurations=" + configurations +
        '}';
  }
}

package com.dasheck.model.models;

import java.util.List;

/**
 * @author Stefan Neidig
 */
public class GameMode {

  private String name;
  private List<Configuration> configurations;
  private List<GameInformation> gameInformationList;
  private List<GameStatistics> gameStatisticses;

  public GameMode() {
  }

  public GameMode(String name, List<Configuration> configurations, List<GameInformation> gameInformationList,
      List<GameStatistics> gameStatisticses) {
    this.name = name;
    this.configurations = configurations;
    this.gameInformationList = gameInformationList;
    this.gameStatisticses = gameStatisticses;
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

  public List<GameStatistics> getGameStatisticses() {
    return gameStatisticses;
  }

  public void setGameStatisticses(List<GameStatistics> gameStatisticses) {
    this.gameStatisticses = gameStatisticses;
  }

  @Override public String toString() {
    return "GameMode{" +
        "name='" + name + '\'' +
        ", configurations=" + configurations +
        ", gameInformationList=" + gameInformationList +
        ", gameStatisticses=" + gameStatisticses +
        '}';
  }
}

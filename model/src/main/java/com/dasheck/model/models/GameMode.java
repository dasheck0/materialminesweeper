package com.dasheck.model.models;

import java.util.List;

/**
 * @author Stefan Neidig
 */
public class GameMode {

  private String name;
  private Configuration configuration;
  private GameInformation gameInformation;
  private GameStatistics gameStatistics;

  public GameMode() {
  }

  public GameMode(String name, Configuration configuration, GameInformation gameInformation,
      GameStatistics gameStatistics) {
    this.name = name;
    this.configuration = configuration;
    this.gameInformation = gameInformation;
    this.gameStatistics = gameStatistics;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Configuration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(Configuration configuration) {
    this.configuration = configuration;
  }

  public GameInformation getGameInformation() {
    return gameInformation;
  }

  public void setGameInformation(GameInformation gameInformation) {
    this.gameInformation = gameInformation;
  }

  public GameStatistics getGameStatistics() {
    return gameStatistics;
  }

  public void setGameStatistics(GameStatistics gameStatistics) {
    this.gameStatistics = gameStatistics;
  }

  @Override public String toString() {
    return "GameMode{" +
        "name='" + name + '\'' +
        ", configuration=" + configuration +
        ", gameInformation=" + gameInformation +
        ", gameStatistics=" + gameStatistics +
        '}';
  }
}

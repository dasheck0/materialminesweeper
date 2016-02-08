package com.dasheck.model.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Stefan Neidig
 */
public class Filter {

  private boolean includeWonGames;
  private boolean includeLostGames;
  private boolean includeEasyGames;
  private boolean includeMediumGames;
  private boolean includeHardGames;
  private boolean includeExpertGames;

  public Filter() {
  }

  public Filter(boolean includeWonGames, boolean includeLostGames, boolean includeEasyGames, boolean includeMediumGames,
      boolean includeHardGames, boolean includeExpertGames) {
    this.includeWonGames = includeWonGames;
    this.includeLostGames = includeLostGames;
    this.includeEasyGames = includeEasyGames;
    this.includeMediumGames = includeMediumGames;
    this.includeHardGames = includeHardGames;
    this.includeExpertGames = includeExpertGames;
  }

  public boolean isIncludeWonGames() {
    return includeWonGames;
  }

  public void setIncludeWonGames(boolean includeWonGames) {
    this.includeWonGames = includeWonGames;
  }

  public boolean isIncludeLostGames() {
    return includeLostGames;
  }

  public void setIncludeLostGames(boolean includeLostGames) {
    this.includeLostGames = includeLostGames;
  }

  public boolean isIncludeEasyGames() {
    return includeEasyGames;
  }

  public void setIncludeEasyGames(boolean includeEasyGames) {
    this.includeEasyGames = includeEasyGames;
  }

  public boolean isIncludeMediumGames() {
    return includeMediumGames;
  }

  public void setIncludeMediumGames(boolean includeMediumGames) {
    this.includeMediumGames = includeMediumGames;
  }

  public boolean isIncludeHardGames() {
    return includeHardGames;
  }

  public void setIncludeHardGames(boolean includeHardGames) {
    this.includeHardGames = includeHardGames;
  }

  public boolean isIncludeExpertGames() {
    return includeExpertGames;
  }

  public void setIncludeExpertGames(boolean includeExpertGames) {
    this.includeExpertGames = includeExpertGames;
  }
}

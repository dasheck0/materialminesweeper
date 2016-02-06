package com.dasheck.model.models;

/**
 * @author Stefan Neidig
 */
public class GameStatistics {

  private int gamesCount;
  private long totalTime;
  private long averageTime;
  private int streak;
  private float winningRate;

  public GameStatistics() {
  }

  public GameStatistics(int gamesCount, long totalTime, long averageTime, int streak, float winningRate) {
    this.gamesCount = gamesCount;
    this.totalTime = totalTime;
    this.averageTime = averageTime;
    this.streak = streak;
    this.winningRate = winningRate;
  }

  public int getGamesCount() {
    return gamesCount;
  }

  public void setGamesCount(int gamesCount) {
    this.gamesCount = gamesCount;
  }

  public long getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(long totalTime) {
    this.totalTime = totalTime;
  }

  public long getAverageTime() {
    return averageTime;
  }

  public void setAverageTime(long averageTime) {
    this.averageTime = averageTime;
  }

  public int getStreak() {
    return streak;
  }

  public void setStreak(int streak) {
    this.streak = streak;
  }

  public float getWinningRate() {
    return winningRate;
  }

  public void setWinningRate(float winningRate) {
    this.winningRate = winningRate;
  }

  @Override public String toString() {
    return "GameStatistics{" +
        "gamesCount=" + gamesCount +
        ", totalTime=" + totalTime +
        ", averageTime=" + averageTime +
        ", streak=" + streak +
        ", winningRate=" + winningRate +
        '}';
  }
}
package com.dasheck.model.models;

/**
 * @author Stefan Neidig
 */
public class BackgroundMusic {

  private String title;
  private String artist;
  private String resourceName;

  public BackgroundMusic() {
  }

  public BackgroundMusic(String title, String artist, String resourceName) {
    this.title = title;
    this.artist = artist;
    this.resourceName = resourceName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    BackgroundMusic that = (BackgroundMusic) o;

    if (!title.equals(that.title)) return false;
    return artist.equals(that.artist);
  }

  @Override public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + artist.hashCode();
    return result;
  }

  @Override public String toString() {
    return "BackgroundMusic{" +
        "title='" + title + '\'' +
        ", artist='" + artist + '\'' +
        ", resourceName='" + resourceName + '\'' +
        '}';
  }
}

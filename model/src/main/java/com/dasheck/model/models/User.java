package com.dasheck.model.models;

/**
 * @author Stefan Neidig
 */
public class User {

  private int id;
  private String name;
  private String image;
  private String uniqueDeviceId;

  public User() {
  }

  public User(int id, String name, String image, String uniqueDeviceId) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.uniqueDeviceId = uniqueDeviceId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getUniqueDeviceId() {
    return uniqueDeviceId;
  }

  public void setUniqueDeviceId(String uniqueDeviceId) {
    this.uniqueDeviceId = uniqueDeviceId;
  }
}

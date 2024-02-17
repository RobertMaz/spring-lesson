package ru.mtsbank.springboottest.data;

import java.util.UUID;

public class User {

  private String name;
  private City city;
  private UUID id;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public City getCity() {
    return city;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}

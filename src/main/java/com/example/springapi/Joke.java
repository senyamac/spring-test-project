package com.example.springapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
  public static final String SINGLE = "single";
  public static final String TWOPART = "twopart";

  private String category;
  private String type;
  private String joke;
  private String setup;
  private String delivery;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getJoke() {
    return joke;
  }

  public void setJoke(String joke) {
    this.joke = joke;
  }

  public String getSetup() {
    return setup;
  }

  public void setSetup(String setup) {
    this.setup = setup;
  }

  public String getDelivery() {
    return delivery;
  }

  public void setDelivery(String delivery) {
    this.delivery = delivery;
  }

  public String toStingOnlyJoke() {
    switch (type) {
      case SINGLE:
        return "Joke: " + joke;
      case TWOPART:
        return "Joke: " + setup + " - " + delivery;
      default:
        return "Joke is empty";
    }
  }

  public String toString() {
    switch (type) {
      case SINGLE:
        return "Joke: " + joke +
            " { type: " + type +
            ", category: " + category + " }";
      case TWOPART:
        return "Joke: " + setup + " - " + delivery +
            " { type: " + type +
            ", category: " + category + " }";
      default:
        return "Joke is empty";
    }
  }
}

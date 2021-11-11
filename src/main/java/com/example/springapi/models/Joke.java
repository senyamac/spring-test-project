package com.example.springapi.models;

import com.example.springapi.util.AppConst;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {


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

  public String getJokeString() {
    switch (type) {
      case AppConst.SINGLE:
        return joke;
      case AppConst.TWOPART:
        return setup + " - " + delivery;
      default:
        return "";
    }
  }

  public String toString() {
    switch (type) {
      case AppConst.SINGLE:
        return "Joke: " + joke +
            "\nAdditions: { type: " + type +
            ", category: " + category + " }";
      case AppConst.TWOPART:
        return "Joke: " + setup + " - " + delivery +
            "\nAdditions: { type: " + type +
            ", category: " + category + " }";
      default:
        return "Joke is empty";
    }
  }
}

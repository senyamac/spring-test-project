package com.example.springapi.models;

import com.example.springapi.interfaces.IJokeAction;
import lombok.NonNull;

public class ReverseJoke implements IJokeAction {
  @Override
  public String actionWithJoke(@NonNull JokeEntity jokeEntity) {
    String strJoke = jokeEntity.getJoke();
    return "Reversed joke: " + new StringBuilder(strJoke).reverse();
  }
}

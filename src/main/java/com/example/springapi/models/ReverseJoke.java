package com.example.springapi.models;

import com.example.springapi.interfaces.IJokeAction;
import lombok.NonNull;

public class ReverseJoke implements IJokeAction {
  @Override
  public String actionWithJoke(@NonNull Joke joke) {
    String strJoke = joke.getJoke();
    return "Reversed joke: " + new StringBuilder(strJoke).reverse();
  }
}

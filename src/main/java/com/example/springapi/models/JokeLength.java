package com.example.springapi.models;

import com.example.springapi.interfaces.IJokeAction;
import lombok.NonNull;

public class JokeLength implements IJokeAction {
  @Override
  public String actionWithJoke(@NonNull Joke joke) {
    String strJoke = joke.getJoke();
    return "Joke length: " + strJoke.length();
  }
}

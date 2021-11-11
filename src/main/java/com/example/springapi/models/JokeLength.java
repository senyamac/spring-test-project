package com.example.springapi.models;

public class JokeLength implements IJokeAction {
  private Joke joke;

  public JokeLength(Joke joke) {
    this.joke = joke;
  }

  @Override
  public void actionWithJoke() {
    String strJoke = joke.getJokeString();
    System.out.println("Joke length: " + strJoke.length());
  }
}

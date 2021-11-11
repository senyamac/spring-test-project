package com.example.springapi.models;

public class ReverseJoke implements IJokeAction {
  private Joke joke;

  public ReverseJoke(Joke joke) {
    this.joke = joke;
  }

  @Override
  public void actionWithJoke() {
    String strJoke = joke.getJokeString();
    System.out.println("Reversed joke: " + new StringBuilder(strJoke).reverse().toString());
  }
}

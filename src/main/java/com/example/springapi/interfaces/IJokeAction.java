package com.example.springapi.interfaces;

import com.example.springapi.models.JokeEntity;

public interface IJokeAction {
  String actionWithJoke(JokeEntity jokeEntity);
}
